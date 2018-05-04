package party.com.br.party.helper;

/**
 * Created by g3infotech on 22/03/18.
 */

public class Constants {
    public static final String SEND_EVENT = "br.com.party.send_event";
    public static final class FIREBASE_REALTIME{
        public static final String CHILD_USER = "user";
        public static final String CHILD_USER_TYPE_BALADA = "balada";
        public static final String CHILD_USER_TYPE_PROMOTOR = "promotor";
        public static final String CHILD_USER_NAME = "name";
        public static final String CHILD_USER_ADRESS = "adress";
        public static final String CHILD_USER_EMAIL = "email";
        public static final String CHILD_USER_PHONE = "phone";
        public static final String CHILD_USER_PICTURE = "picture";
        public static final String CHILD_USER_TYPE = "TYPE";
        public static final String CHILD_USER_INTEREST = "interest";
    }
    public static final class INTRO{
        public static final String SEND_EMAIL = "br.com.party.intro.email";
        public static final String SEND_TYPE = "br.com.party.intro.type";
    }
    public static final class PREFERENCES{
        public static final String PREFERENCES_NAME = "br.com.party.preferences.name";
        public static final String PREFERENCES_ID = "br.com.party.preferences.id";
        public static final String PREFERENCES_EMAIL = "br.com.party.preferences.email";
        public static final int MODE = 0;
    }
}
