package com.itcast.dictionary.Models;

import java.util.List;

public class APIResponse {
    // 网址：dictionaryapi.dev
    String word="";
//    String phonetic="";
    String origin="";
    List<Phonetics> phonetics=null;
    List<Meanings> meanings=null;

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }


    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public List<Phonetics> getPhonetics() {
        return phonetics;
    }

    public void setPhonetics(List<Phonetics> phonetics) {
        this.phonetics = phonetics;
    }

    public List<Meanings> getMeanings() {
        return meanings;
    }

    public void setMeanings(List<Meanings> meanings) {
        this.meanings = meanings;
    }
}
