package xyz.aiinirii.postalk.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.aiinirii.postalk.mapper.PostMapper;

/**
 * @author AIINIRII
 */
@Service
public class PostService {

    private PostMapper postMapper;

    @Autowired
    public void setPostMapper(PostMapper postMapper) {
        this.postMapper = postMapper;
    }
}