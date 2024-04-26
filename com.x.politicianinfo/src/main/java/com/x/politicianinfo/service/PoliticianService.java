package com.x.politicianinfo.service;

import com.x.politicianinfo.data.Party;
import com.x.politicianinfo.data.Politician;

import java.util.*;
import java.util.stream.Collectors;

public class PoliticianService {

    private Map<String, Politician> politicians = new HashMap<>();
    private Map<String, Party> parties = new HashMap<>();

    public Politician addPolitician(Politician politician) {
        if (politician != null && !politicians.containsKey(politician.getId())) {
            politicians.put(politician.getId(), politician);
            return politician;
        }
        return null;
    }

    public Politician getPoliticianById(String id) {
        return politicians.get(id);
    }

    public Collection<Politician> getPoliticianByName(String name) {
        // 获取指定名称的政治家信息
        return politicians.values().stream()
                .filter(p -> p.getName().equalsIgnoreCase(name))
                .collect(Collectors.toList());
    }

    public Collection<Politician> getAllPoliticians() {
        // 返回所有政治家信息
        return politicians.values();
    }

    public Collection<Politician> searchPoliticiansByParty(String partyId) {
        // 根据党派搜索政治家
        List<Politician> result = new ArrayList<>();
        for (Politician p : politicians.values()) {
            if (partyId.equals(p.getPartyId())) {
                result.add(p);
            }
        }
        return result;
    }

    public Collection<Politician> searchPoliticiansByName(String name) {
        // 根据名称模糊搜索政治家
        return politicians.values().stream()
                .filter(p -> p.getName().toLowerCase().contains(name.toLowerCase()))
                .collect(Collectors.toList());
    }

    public boolean deletePoliticianById(String id) {
        if (politicians.containsKey(id)) {
            politicians.remove(id);
            return true;
        }
        return false;
    }

    public Party addParty(Party party) {
        // 添加党派信息
        if (party != null && !parties.containsKey(party.getId())) {
            parties.put(party.getId(), party);
            return party;
        }
        return null;
    }
    public Party updateParty(String partyId, Party updatedParty) {
        if (parties.containsKey(partyId)) {
            parties.put(partyId, updatedParty);
            return updatedParty;
        }
        return null; 
    }

    public boolean deleteParty(String partyId) {
        if (parties.containsKey(partyId)) {
            parties.remove(partyId);
            return true;
        }
        return false; 
    }
    public Collection<Party> getAllParties() {
        return Collections.unmodifiableCollection(parties.values());
      }
    
}