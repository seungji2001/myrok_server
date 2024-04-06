package com.example.myrok.service;

import com.example.myrok.domain.Tag;

import java.util.List;

public interface TagService {
    Tag save(String tagName);
    void delete(List<Tag> tagList);
}
