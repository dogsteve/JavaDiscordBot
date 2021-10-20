package contentfilter;

import entities.MessageFilter;

import java.util.ArrayList;
import java.util.List;

public class TextMessageFilter {

    private static List<MessageFilter> messageFilter = new ArrayList<MessageFilter>();

    public static void initDefaultFilter() {
        messageFilter.add(new MessageFilter("dit me may", "Con chó này chửi bậy"));
        messageFilter.add(new MessageFilter("cac", "Con chó này chửi bậy"));
        messageFilter.add(new MessageFilter("djt", "Con chó này chửi bậy"));
        messageFilter.add(new MessageFilter("loz", "Con chó này chửi bậy"));
        messageFilter.add(new MessageFilter("buoi", "Con chó này chửi bậy"));
    }

    public static String addNewFilter(MessageFilter newFilter) {
        if (messageFilter.stream().anyMatch(object -> {
            return object.getContext().equals(newFilter.getContext()) || object.getType().equals(newFilter.getType());
        })) {
            return "Douplicate key, please use another word";
        }
        messageFilter.add(newFilter);
        return "Done bro";
    }

    public static String removeFilter(String keyWord) {
        if (messageFilter.stream().anyMatch(object -> {
            return object.getContext().equals(keyWord.trim());
        })) {
            return "Douplicate key, please try another word";
        }
        messageFilter.remove(messageFilter.stream().filter(
                object -> {
                    return object.getContext().equals(keyWord);
                }
        ).findAny());
        return "Removed";
    }

    public static List<MessageFilter> getMessageFilter() {
        return messageFilter;
    }

}
