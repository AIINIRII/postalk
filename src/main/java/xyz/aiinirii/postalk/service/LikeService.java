package xyz.aiinirii.postalk.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import xyz.aiinirii.postalk.bean.Like;
import xyz.aiinirii.postalk.mapper.LikeMapper;

/**
 * @author AIINIRII
 */
@Service
public class LikeService {

    LikeMapper likeMapper;

    @Autowired
    public void setLikeMapper(LikeMapper likeMapper) {
        this.likeMapper = likeMapper;
    }

    /**
     * add like if the user haven't liked,
     * delete like if the user already have liked
     *
     * @param tid text's id
     * @param uid user's id
     * @return true if the operation is success
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean like(Integer tid, Integer uid) {
        Like like = likeMapper.findLikeByTIdUId(tid, uid);
        if (like == null) {
            return likeMapper.insertLike(tid, uid) == 1;
        } else {
            return likeMapper.deleteLike(tid, uid) == 1;
        }
    }
}