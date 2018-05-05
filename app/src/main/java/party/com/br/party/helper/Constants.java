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
        public static final String CHILD_USER_TYPE = "type";
        public static final String CHILD_USER_TEXT = "text";
        public static final String CHILD_USER_INTEREST = "interest";
        public static final String CHILD_USER_STATUS = "status";
    }
    public static final class TYPE_INTEREST{
        public static final String SERTANEJO = "SERTANEJO";
        public static final String ROCK = "ROCK";
        public static final String MPB = "MPB";
        public static final String SAMBA = "SAMBA";
        public static final String FORRO = "FORRO";
        public static final String ELETRONICA = "ELETRONICA";
    }
    public static final class TEXTS_STATUS{
        public static final String OLA = "OLÁ, TUDO BEM?";
        public static final String TRABALHANDO = "TRABALHANDO";
        public static final String OCUPADO = "OCUPADO";
        public static final String DIRIGINDO = "DIRIGINDO";
        public static final String VIAJANDO = "VIAJANDO";
    }
    public static final class FIREBASE_STORAGE{
        public static final String CHILD_PHOTO_PROFILE = "photo_profile";
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

    public static final class PICTURE {
        public static final int SELECT_IMAGE = 11;
    }
}
