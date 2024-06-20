package com.example.guessr;

import android.content.Context;
import android.content.res.XmlResourceParser;
import android.util.Log;

import org.xmlpull.v1.XmlPullParser;
import java.util.ArrayList;
import java.util.List;

public class XMLParser {

    public static List<Flashcard> parseFlashcards(Context context) {
        List<Flashcard> flashcards = new ArrayList<>();
        XmlResourceParser parser = context.getResources().getXml(R.xml.flashcards);

        try {
            int eventType = parser.getEventType();
            Flashcard currentFlashcard = null;
            while (eventType != XmlPullParser.END_DOCUMENT) {
                String tagName;
                switch (eventType) {
                    case XmlPullParser.START_TAG:
                        tagName = parser.getName();
                        if (tagName.equals("flashcard")) {
                            currentFlashcard = new Flashcard("", "");
                        } else if (currentFlashcard != null) {
                            if (tagName.equals("question")) {
                                currentFlashcard.setQuestion(parser.nextText());
                            } else if (tagName.equals("answer")) {
                                currentFlashcard.setAnswer(parser.nextText());
                            }
                        }
                        break;

                    case XmlPullParser.END_TAG:
                        tagName = parser.getName();
                        if (tagName.equalsIgnoreCase("flashcard") && currentFlashcard != null) {
                            flashcards.add(currentFlashcard);
                        }
                        break;
                }
                eventType = parser.next();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            parser.close();
        }
        return flashcards;

    }
}
