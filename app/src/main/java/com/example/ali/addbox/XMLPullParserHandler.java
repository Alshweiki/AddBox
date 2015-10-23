package com.example.ali.addbox;

/**
 * Created by Ali on 10/23/15.
 */
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;




public class XMLPullParserHandler {
    List<Boxes> boxes;
    private Boxes box;
    private String text;

    public XMLPullParserHandler() {
        boxes = new ArrayList<Boxes>();
    }

    public List<Boxes> getboxes() {
        return boxes;
    }

    public List<Boxes> parse(InputStream is) {
        boxes = new ArrayList<Boxes>();
        XmlPullParserFactory factory = null;
        XmlPullParser parser = null;
        try {
            factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(true);
            parser = factory.newPullParser();

            parser.setInput(is, null);

            int eventType = parser.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT) {
                String tagname = parser.getName();
                switch (eventType) {
                    case XmlPullParser.START_TAG:
                        if (tagname.equalsIgnoreCase("box")) {
                            // create a new instance of box
                            box = new Boxes();
                        }
                        break;

                    case XmlPullParser.TEXT:
                        text = parser.getText();
                        break;

                    case XmlPullParser.END_TAG:
                        if (tagname.equalsIgnoreCase("box")) {
                            // add box object to list
                            boxes.add(box);
                        } else if (tagname.equalsIgnoreCase("value")) {
                            box.setvalue(text);
                        }
                        else if (tagname.equalsIgnoreCase("id")) {
                            box.setid(Integer.valueOf(text));
                        }
                        break;

                    default:
                        break;
                }
                eventType = parser.next();
            }

        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return boxes;
    }
}
