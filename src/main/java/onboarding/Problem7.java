package onboarding;

import java.util.*;

public class Problem7 {
    public static List<String> solution(String user, List<List<String>> friends, List<String> visitors) {
        List<String> answer = Collections.emptyList();

        HashSet<String> userFriends = makeUserFriendList(user, friends);
        HashMap<String, Integer> notUserFriendsScore = makeNotUserFriendList(user, friends, visitors, userFriends);

        calcFriendsScore(userFriends, notUserFriendsScore, friends);

        return answer;
    }

    public static void calcFriendsScore(HashSet<String> userFriends, HashMap<String, Integer> notUserFriendsScore, List<List<String>> friends) {
        for (String notFriendId : notUserFriendsScore.keySet()) {
            for (String friendId : userFriends) {
                for (List<String> pair : friends) {
                    if (isFriend(notFriendId, friendId, pair)) {
                        notUserFriendsScore.put(notFriendId, notUserFriendsScore.get(notFriendId) + 10);
                        break;
                    }
                }
            }
        }
    }

    public static HashSet<String> makeUserFriendList(String user, List<List<String>> friends) {
        HashSet<String> userFriends = new HashSet<>();

        for (List<String> pair : friends) {
            if (isUserFriend(user, pair)) {
                userFriends.add(getUserFriend(user, pair));
            }
        }
        return userFriends;
    }

    public static HashMap<String, Integer> makeNotUserFriendList(String user, List<List<String>> friends, List<String> visitors, HashSet<String> userFriends) {
        HashMap<String, Integer> notUserFriends = new HashMap<>();

        for (List<String> pair : friends) {
            if (!isUserFriend(user, pair)) {
                ArrayList<String> notUserFriend = getNotUserFriend(userFriends, pair);
                for (String notFriend : notUserFriend) {
                    notUserFriends.put(notFriend, 0);
                }
            }
        }

        for (String id : visitors) {
            if (!userFriends.contains(id)) {
                notUserFriends.put(id, 0);
            }
        }

        notUserFriends.remove(user);

        return notUserFriends;
    }

    public static boolean isFriend(String user1, String user2, List<String> pair) {
        String tmp1 = pair.get(0);
        String tmp2 = pair.get(1);

        if (user1.equals(tmp1) && user2.equals(tmp2)) {
            return true;
        }
        if (user1.equals(tmp2) && user2.equals(tmp1)) {
            return true;
        }

        return false;
    }

    public static boolean isUserFriend(String user, List<String> pair) {
        for (String id : pair) {
            if (id.equals(user)) {
                return true;
            }
        }
        return false;
    }

    public static String getUserFriend(String user, List<String> pair) {
        return pair.get(0).equals(user) ? pair.get(1) : pair.get(0);
    }

    public static ArrayList<String> getNotUserFriend(HashSet<String> userFriends, List<String> pair) {
        ArrayList<String> notFriendsId = new ArrayList<>(2);
        for (String id : pair) {
            if (!userFriends.contains(id)) {
                notFriendsId.add(id);
            }
        }
        return notFriendsId;
    }
}
