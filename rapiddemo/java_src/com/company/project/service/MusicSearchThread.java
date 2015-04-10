package com.company.project.service;

import com.company.project.model.resp.Music;
import org.dom4j.DocumentException;

import java.io.IOException;

/**
 * Created by qince on 2015/4/7.
 */
public class MusicSearchThread extends Thread {
    private Music music;
    private String author;
    private String title;
    public MusicSearchThread(Music music,String author,String title) {
        this.music = music;
        this.author = author;
        this.title = title;
    }
    @Override
    public void run() {
        try {
            music = MusicSearchService.searchMusicByBaidu(author, title);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }
}
