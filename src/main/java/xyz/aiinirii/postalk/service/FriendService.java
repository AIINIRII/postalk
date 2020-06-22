package xyz.aiinirii.postalk.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import xyz.aiinirii.postalk.bean.Friend;
import xyz.aiinirii.postalk.bean.User;
import xyz.aiinirii.postalk.mapper.FriendMapper;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * @author AIINIRII
 */
@Service
public class FriendService {

    private FriendMapper friendMapper;

    @Autowired
    public void setFriendMapper(FriendMapper friendMapper) {
        this.friendMapper = friendMapper;
    }

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<User> findAllFriendsByUId(Integer id) {
        List<User> resultList = new LinkedList<>();
        List<Friend> friends = friendMapper.findAllFriendsByUId(id);
        for (Friend friend : friends) {
            User user1 = friend.getUser1();
            User user2 = friend.getUser2();
            resultList.add(user1.getId().equals(id) ? user2 : user1);
        }
        return resultList;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void addFriend(Integer id1, Integer id2) {
        // get now time
        Date now = new Date(System.currentTimeMillis());
        friendMapper.insertFriendByUId(id1, id2, now);
    }

    public boolean deleteFriendById(Integer id, Integer id1) {
        return friendMapper.deleteFriendById(id, id1) == 1;
    }
}