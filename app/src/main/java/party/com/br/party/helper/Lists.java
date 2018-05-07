package party.com.br.party.helper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Isabelly on 05/05/2018.
 */

public class Lists {

    public static List<String> getStates(){
        List<String> states = new ArrayList<>();
        states.add(Constants.STATES.ACRE);
        states.add(Constants.STATES.ALAGOAS);
        states.add(Constants.STATES.AMAPA);
        states.add(Constants.STATES.AMAZONAS);
        states.add(Constants.STATES.BAHIA);
        states.add(Constants.STATES.CEARA);
        states.add(Constants.STATES.DISTRITO_FEDERAL);
        states.add(Constants.STATES.ESPITITO_SANTO);
        states.add(Constants.STATES.GOIAS);
        states.add(Constants.STATES.MARANHAO);
        states.add(Constants.STATES.MATO_GROSSO);
        states.add(Constants.STATES.MATO_GROSSO_SUL);
        states.add(Constants.STATES.MINAS_GERAIS);
        states.add(Constants.STATES.PARA);
        states.add(Constants.STATES.PARAIBA);
        states.add(Constants.STATES.PARANA);
        states.add(Constants.STATES.PERNAMBUCO);
        states.add(Constants.STATES.PIAUI);
        states.add(Constants.STATES.RIO_GRANDE_NORTE);
        states.add(Constants.STATES.RIO_GRANDE_SUL);
        states.add(Constants.STATES.RIO_JANEIRO);
        states.add(Constants.STATES.RONDONIA);
        states.add(Constants.STATES.RORAIMA);
        states.add(Constants.STATES.SANTA_CATARINA);
        states.add(Constants.STATES.SAO_PAULO);
        states.add(Constants.STATES.SERGIPE);
        states.add(Constants.STATES.TOCANTINS);
        return states;
    }

    public static List<String> getTypes(){
        List<String> types = new ArrayList<>();
        types.add(Constants.TYPE_INTEREST.ELETRONICA);
        types.add(Constants.TYPE_INTEREST.FORRO);
        types.add(Constants.TYPE_INTEREST.MPB);
        types.add(Constants.TYPE_INTEREST.ROCK);
        types.add(Constants.TYPE_INTEREST.SAMBA);
        types.add(Constants.TYPE_INTEREST.SERTANEJO);
        return types;
    }

}
