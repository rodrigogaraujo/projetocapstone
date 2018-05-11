package party.com.br.party.helper;

/**
 * Created by g3infotech on 22/03/18.
 */

public class Constants {
    public static final String SEND_EVENT = "br.com.party.send_event";
    public static final String SEND_EVENTS = "br.com.party.send_events";
    public static final String SEND_EVENT_NOTIFICATION = "br.com.party.send_event_notification";
    public static final String SEND_EVENT_NOTIFICATION_OK = "notification_ok";
    public static final String SEND_EVENT_DAY = "br.com.party.send_event_day";
    public static final String SEND_EVENT_LOCALE = "br.com.party.send_event_locale";
    public static final String SEND_PERSON = "br.com.party.send_person";
    public static final String LIST_STATE_KEY = "br.com.party.list_state";
    public static final String TAG_DISPATCHER = "br.com.party.tag";
    public static final String SEND_DAY = "br.com.party.send_day";

    public static final class FIREBASE_REALTIME {
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

        public static final String CHILD_EVENT = "event";
        public static final String CHILD_EVENT_DESCRIPTION = "description";
        public static final String CHILD_EVENT_DATE = "date";
        public static final String CHILD_EVENT_ADRESS = "adress";
        public static final String CHILD_EVENT_EMAIL = "email";
        public static final String CHILD_EVENT_CONTACT = "contact";
        public static final String CHILD_EVENT_PICTURE = "picture";
        public static final String CHILD_EVENT_TYPE = "type";
        public static final String CHILD_EVENT_DAYS = "days";
        public static final String CHILD_EVENT_HOURS = "hours";
        public static final String CHILD_EVENT_LOCALE_TICKETS = "localeTickets";
        public static final String CHILD_EVENT_LOCATION = "location";
        public static final String CHILD_EVENT_NAME = "name";
        public static final String CHILD_EVENT_PERSON_GO = "idPersonGo";
    }

    public static final class TYPE_INTEREST {
        public static final String SERTANEJO = "SERTANEJO";
        public static final String ROCK = "ROCK";
        public static final String MPB = "MPB";
        public static final String SAMBA = "SAMBA";
        public static final String FORRO = "FORRO";
        public static final String ELETRONICA = "ELETRONICA";
    }

    public static final class TEXTS_STATUS {
        public static final String OLA = "OLÁ, TUDO BEM?";
        public static final String TRABALHANDO = "TRABALHANDO";
        public static final String OCUPADO = "OCUPADO";
        public static final String DIRIGINDO = "DIRIGINDO";
        public static final String VIAJANDO = "VIAJANDO";
    }

    public static final class FIREBASE_STORAGE {
        public static final String CHILD_PHOTO_PROFILE = "photo_profile";
        public static final String CHILD_PHOTO_BANNER_DAY = "photo_banner_day";
        public static final String CHILD_PHOTO_BANNER = "photo_banner";
    }

    public static final class INTRO {
        public static final String SEND_EMAIL = "br.com.party.intro.email";
        public static final String SEND_TYPE = "br.com.party.intro.type";
    }

    public static final class PREFERENCES {
        public static final String PREFERENCES_NAME = "br.com.party.preferences.name";
        public static final String PREFERENCES_ID = "br.com.party.preferences.id";
        public static final String PREFERENCES_EMAIL = "br.com.party.preferences.email";
        public static final int MODE = 0;
    }

    public static final class PICTURE {
        public static final int SELECT_IMAGE = 11;
    }

    public static final class EVENT_ACTION {
        public static final int ADD_DAY = 12;
        public static final int ADD_LOCALE = 13;
        public static final String SEND_EVENT_WIDGET = "event_widget";
    }

    public static final class STATES {
        public static final String ACRE = "Acre";
        public static final String ALAGOAS = "Alagoas";
        public static final String AMAPA = "Amapá";
        public static final String AMAZONAS = "Amazonas";
        public static final String BAHIA = "Bahia";
        public static final String CEARA = "Ceará";
        public static final String DISTRITO_FEDERAL = "Distrito Federal";
        public static final String ESPITITO_SANTO = "Espírito Santo";
        public static final String GOIAS = "Goiás";
        public static final String MARANHAO = "Maranhão";
        public static final String MATO_GROSSO = "Mato Grosso";
        public static final String MATO_GROSSO_SUL = "Mato Grosso do Sul";
        public static final String MINAS_GERAIS = "Minas Gerais";
        public static final String PARA = "Pará";
        public static final String PARAIBA = "Paraíba";
        public static final String PARANA = "Paraná";
        public static final String PERNAMBUCO = "Pernambuco";
        public static final String PIAUI = "Piauí";
        public static final String RIO_JANEIRO = "Rio de Janeiro";
        public static final String RIO_GRANDE_NORTE = "Rio Grande do Norte";
        public static final String RIO_GRANDE_SUL = "Rio Grande do Sul";
        public static final String RONDONIA = "Rondônia";
        public static final String RORAIMA = "Roraima";
        public static final String SANTA_CATARINA = "Santa Catarina";
        public static final String SAO_PAULO = "São Paulo";
        public static final String SERGIPE = "Sergipe";
        public static final String TOCANTINS = "Tocantins";
    }
}
