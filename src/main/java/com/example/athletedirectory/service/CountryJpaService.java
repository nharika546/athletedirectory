package com.example.athletedirectory.service;

import com.example.athletedirectory.model.Athlete;
import com.example.athletedirectory.model.Country;
import com.example.athletedirectory.repository.AthleteJpaRepository;
import com.example.athletedirectory.repository.CountryJpaRepository;
import com.example.athletedirectory.repository.CountryRepository;
import com.example.athletedirectory.repository.AthleteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class CountryJpaService implements CountryRepository {

    @Autowired
    private CountryJpaRepository countryJpaRepository;

    @Autowired
    private AthleteJpaRepository athleteJpaRepository;

    @Override
    public ArrayList<Country> getCountries() {
        List<Country> countryList = countryJpaRepository.findAll();
        ArrayList<Country> countries = new ArrayList<>(countryList);
        return countries;
    }

    @Override
    public Country getCountryById(int countryId) {
        try {
            return countryJpaRepository.findById(countryId).get();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public Country addCountry(Country country) {
        return countryJpaRepository.save(country);
    }

    @Override
    public Country updateCountry(int countryId, Country country) {
        try {
            Country newCountry = countryJpaRepository.findById(countryId).get();
            if (country.getName() != null) {
                newCountry.setName(country.getName());
            }
            if (country.getFlagImageUrl() != null) {
                newCountry.setFlagImageUrl(country.getFlagImageUrl());
            }
            return countryJpaRepository.save(newCountry);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public void deleteCountry(int countryId) {
        Country p = getCountryById(countryId);
        if (p == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        else {
            countryJpaRepository.deleteById(countryId);
            throw new ResponseStatusException(HttpStatus.NO_CONTENT);
        }
    }

    @Override
    public List<Athlete> getCountryAthletes(int countryId) {
        try {
            ArrayList<Athlete> output = new ArrayList<>();
            List<Athlete> athle = athleteJpaRepository.findAll();
            ArrayList<Athlete> ath = new ArrayList<>(athle);
            int si = ath.size();
            for (int i = 0; i < si; i++) {
                Athlete athlete = ath.get(i);
                Country cou = athlete.getCountry();
                if (cou.getId() == countryId)
                    output.add(athlete);
            }
            return output;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}