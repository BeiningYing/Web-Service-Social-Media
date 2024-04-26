package com.x.politicianinfo.soap;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

@WebService
public class PoliticianSoap {

    private static final Logger LOGGER = Logger.getLogger(PoliticianSoap.class.getName());

    private static final Map<Integer, String> politicianMap = new HashMap<Integer, String>();
    private static final Map<Integer, String> politicianPartyMap = new HashMap<Integer, String>(); 
    static {
        initializePoliticianMap();
    }

    private static void initializePoliticianMap() {
        politicianMap.put(1, "Politician A");
        politicianMap.put(2, "Politician B");
        politicianMap.put(3, "Politician C");
        politicianPartyMap.put(1, "Party X");
        politicianPartyMap.put(2, "Party Y");
        politicianPartyMap.put(3, "Party Z");

        LOGGER.info("Politician map initialized successfully.");
    }

    @WebMethod(operationName = "GetPoliticianById")
    @WebResult(name = "Politician")
    public String getPoliticianById(@WebParam(name = "id") int id) {
        String politicianInfo = politicianMap.get(id);
        if (politicianInfo != null) {
            LOGGER.info("Politician found for ID: " + id);
            return politicianInfo;
        } else {
            LOGGER.warning("No politician found for ID: " + id);
            return "No politician found for ID: " + id;
        }
    }

    @WebMethod(operationName = "GetAllPoliticians")
    @WebResult(name = "Politicians")
    public String getAllPoliticians() {
        StringBuilder allPoliticiansInfo = new StringBuilder();
        for (Map.Entry<Integer, String> entry : politicianMap.entrySet()) {
            allPoliticiansInfo.append("ID: ").append(entry.getKey()).append(" - ").append(entry.getValue()).append(" (").append(politicianPartyMap.get(entry.getKey())).append(")").append("\n");
        }
        LOGGER.info("All politicians information retrieved.");
        return allPoliticiansInfo.toString();
    }

    @WebMethod(operationName = "AddPoliticianPartyMapping") 
    @WebResult(name = "Result")
    public String addPoliticianPartyMapping(@WebParam(name = "id") int id, @WebParam(name = "party") String party) {
        if (politicianMap.containsKey(id)) {
            if (party != null && !party.isEmpty()) {
                politicianPartyMap.put(id, party);
                LOGGER.info("Party mapping added for Politician ID: " + id + " -> " + party);
                return "Party mapping added successfully.";
            } else {
                LOGGER.warning("Invalid party name provided for Politician ID: " + id);
                return "Invalid party name provided.";
            }
        } else {
            LOGGER.warning("No politician found for ID: " + id + ". Cannot add party mapping.");
            return "No politician found for ID: " + id + ". Cannot add party mapping.";
        }
    }
}
