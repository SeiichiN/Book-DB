package controller;

import com.fasterxml.jackson.databind.JsonNode;

import api.BookInfo;
import beans.BookBean;
import java.io.UnsupportedEncodingException;

public class BookController {

    public void execute(BookBean bookBean) {
        // String isbn = "9784798062082";
        String isbn = bookBean.getIsbn();
        String url = "https://api.openbd.jp/v1/get?isbn=" + isbn;
        BookInfo info = new BookInfo();

        JsonNode node = null;
        try {
            node = info.getResult(url);
            System.out.println("node:" + node);

            if (node.get(0) != null) {
                JsonNode obj = node.get(0);
                if (obj.has("summary")) {
                    JsonNode summary = obj.get("summary");
                    bookBean.setIsbn(isbn);
                    if (summary.has("title")) {
                        String _title = summary.get("title").toString();
                        String title = trimDQ(_title);
                        bookBean.setTitle(title);
                    }
                    if (summary.has("volume")) {
                        String volume = summary.get("volume").toString();
                        bookBean.setVolume(volume);
                    }
                    if (summary.has("series")) {
                        String series = summary.get("series").toString();
                        bookBean.setSeries(series);
                    }
                    if (summary.has("publisher")) {
                        String _publisher = summary.get("publisher").toString();
                        String publisher = trimDQ(_publisher);
                        bookBean.setPublisher(publisher);
                    }
                    if (summary.has("pubdate")) {
                        String _pubdate = summary.get("pubdate").toString();
                        String pubdate = trimDQ(_pubdate);
                        bookBean.setPubdate(pubdate);
                    }
                    if (summary.has("series")) {
                        String series = summary.get("series").toString();
                        bookBean.setSeries(series);
                    }
                    if (summary.has("cover")) {
                        String cover = summary.get("cover").toString();
                        System.out.println(cover);
                        bookBean.setCover(trimDQ(cover));
                    }
                    if (summary.has("author")) {
                        String _author = summary.get("author").toString();
                        String author = trimDQ(_author);
                        bookBean.setAuthor(author);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(System.getProperty("file.encoding"));
    }
    
    public boolean isSJIS () {
        if (System.getProperty("file.encoding").equals("MS932") ) {
            return true;
        }
        return false;
    }

    public String toUTF8(String sText) {
        try {
            byte[] s = sText.getBytes("SJIS");
            return new String(s, "UTF-8");
        } catch (UnsupportedEncodingException e) {
        }
        return null;
    }

    /**
     * 文字列前後のダブルクォーテーションを削除するFunction
     *
     * @param str 文字列
     * @return 前後のダブルクォーテーションを削除した文字列
     */
    public String trimDQ(String str) {
        char c = '"';
        if (str.charAt(0) == c && str.charAt(str.length() - 1) == c) {
            return str.substring(1, str.length() - 1);
        } else {
            return str;
        }
    }
}
