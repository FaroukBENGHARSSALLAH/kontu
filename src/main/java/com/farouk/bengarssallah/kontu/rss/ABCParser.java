package com.farouk.bengarssallah.kontu.rss;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Iterator;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.Characters;
import java.io.IOException;
import javax.xml.stream.events.XMLEvent;
import javax.xml.stream.XMLEventReader;
import java.io.InputStream;
import java.text.ParseException;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLInputFactory;
import java.util.LinkedList;
import java.net.MalformedURLException;
import java.net.URL;

public class ABCParser
{
    private static final String rss_feed = " RSS Feed";
    private static final String date_format = "EEE, dd MMM yyyy HH:mm:ss";
    private static final String attr_url = "url";
    protected final URL url;
    
    public ABCParser(final String feedUrl) {
        try {
            this.url = new URL(feedUrl);
        }
        catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public LinkedList<ABCNews> getNews() {
        final LinkedList<ABCNews> list = new LinkedList<ABCNews>();
        try {
            boolean isHeader = true;
            String title = null;
            String category = null;
            String description = null;
            String date = null;
            String link = null;
            String thumbnail_picture = null;
            String content_picture_1210 = null;
            String content_picture_1211 = null;
            String content_picture_1212 = null;
            String content_picture_1213 = null;
            String content_picture_1214 = null;
            final XMLInputFactory inputFactory = XMLInputFactory.newInstance();
            final InputStream in = this.read();
            final XMLEventReader eventReader = inputFactory.createXMLEventReader(in);
            while (eventReader.hasNext()) {
                XMLEvent event = eventReader.nextEvent();
                if (event.isStartElement()) {
                    final String localPart = event.asStartElement().getName().getLocalPart();
                    if (localPart.equals("channel") && isHeader) {
                        event = eventReader.nextEvent();
                        event = eventReader.nextEvent();
                        category = this.getCharacterData(event, eventReader);
                        for (int i = 0; i < 14; ++i) {
                            event = eventReader.nextEvent();
                        }
                        isHeader = false;
                    }
                    if (localPart.equals("item")) {
                        event = eventReader.nextEvent();
                    }
                    if (localPart.equals("title")) {
                        title = this.getCharacterData(event, eventReader);
                    }
                    if (localPart.equals("link")) {
                        link = this.getCharacterData(event, eventReader);
                    }
                    if (localPart.equals("description")) {
                        description = this.getCharacterData(event, eventReader);
                    }
                    if (localPart.equals("pubDate")) {
                        date = this.getCharacterData(event, eventReader);
                    }
                    if (localPart.equals("content")) {
                        if (content_picture_1210 == null) {
                            content_picture_1210 = this.getPictureURL(event, eventReader);
                        }
                        else if (content_picture_1211 == null) {
                            content_picture_1211 = this.getPictureURL(event, eventReader);
                        }
                        else if (content_picture_1212 == null) {
                            content_picture_1212 = this.getPictureURL(event, eventReader);
                        }
                        else if (content_picture_1213 == null) {
                            content_picture_1213 = this.getPictureURL(event, eventReader);
                        }
                        else if (content_picture_1214 == null) {
                            content_picture_1214 = this.getPictureURL(event, eventReader);
                        }
                    }
                    if (!localPart.equals("thumbnail")) {
                        continue;
                    }
                    thumbnail_picture = this.getPictureURL(event, eventReader);
                }
                else {
                    if (!event.isEndElement() || event.asEndElement().getName().getLocalPart() != "item") {
                        continue;
                    }
                    final ABCNews news = new ABCNews();
                    news.setTitle(title);
                    news.setCategory(category.replace(" RSS Feed", ""));
                    news.setDescription(description);
                    news.setDate(this.formatDate(date.substring(0, date.lastIndexOf(":") + 2), "EEE, dd MMM yyyy HH:mm:ss"));
                    news.setLink(link);
                    news.setThumbnail_picture(thumbnail_picture);
                    news.setContent_picture_1210(content_picture_1210);
                    news.setContent_picture_705(content_picture_1211);
                    news.setContent_picture_627(content_picture_1212);
                    news.setContent_picture_1253(content_picture_1213);
                    news.setContent_picture_1400(content_picture_1214);
                    if (news.getContent_picture_627() != null) {
                        news.setImage(news.getContent_picture_627());
                    }
                    else if (news.getContent_picture_705() != null) {
                        news.setImage(news.getContent_picture_705());
                    }
                    else if (news.getContent_picture_1210() != null) {
                        news.setImage(news.getContent_picture_1210());
                    }
                    else if (news.getContent_picture_1253() != null) {
                        news.setImage(news.getContent_picture_1253());
                    }
                    else if (news.getContent_picture_1400() != null) {
                        news.setImage(news.getContent_picture_1400());
                    }
                    news.setReference(this.generateReference(news, "a23"));
                    list.add(news);
                    thumbnail_picture = null;
                    content_picture_1210 = null;
                    content_picture_1211 = null;
                    content_picture_1212 = null;
                    content_picture_1213 = null;
                    content_picture_1214 = null;
                    event = eventReader.nextEvent();
                }
            }
        }
        catch (XMLStreamException e) {
            throw new RuntimeException(e);
        }
        catch (ParseException e2) {
            e2.printStackTrace();
        }
        return list;
    }
    
    protected InputStream read() {
        try {
            return this.url.openStream();
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    
    protected String getCharacterData(XMLEvent event, final XMLEventReader eventReader) throws XMLStreamException {
        String result = "";
        event = eventReader.nextEvent();
        if (event instanceof Characters) {
            result = event.asCharacters().getData();
        }
        return result;
    }
    
    protected String getPictureURL(final XMLEvent event, final XMLEventReader eventReader) throws XMLStreamException {
        final Iterator<Attribute> attributes = event.asStartElement().getAttributes();
        while (attributes.hasNext()) {
            final Attribute attribute = attributes.next();
            if (attribute.getName().getLocalPart().equals("url")) {
                return attribute.getValue();
            }
        }
        return null;
    }
    
    protected String formatDate(final String date_value, final String format) throws ParseException {
        final Date date = new SimpleDateFormat(format, Locale.ENGLISH).parse(date_value);
        return new SimpleDateFormat("HH:mm dd/MM").format(date);
    }
    
    protected String generateReference(final ABCNews news, final String source) {
        final String cat = news.getCategory().substring(0, 3).toLowerCase();
        final String dat = news.getDate().replace(":", "").replace("/", "").replace(" ", "");
        return cat + dat + source;
    }
}