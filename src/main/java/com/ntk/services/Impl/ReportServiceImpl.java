package com.ntk.services.Impl;

import com.ntk.controllers.UtilsController;
import com.ntk.pojos.Report;
import com.ntk.repositories.ReportRepository;
import com.ntk.services.ReportService;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReportServiceImpl implements ReportService{

    @Autowired
    private ReportRepository reportRepository;

    @Override
    @Transactional
    public List<JSONObject> getReports() {
        List<Report> reports = reportRepository.getReports();
        List<JSONObject> jsonObjects = new ArrayList<>();
        reports.forEach(e->{
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", UtilsController.encodeBase64(String.valueOf(e.getReportId())));
            jsonObject.put("name", e.getName());
            jsonObjects.add(jsonObject);
        });
        return jsonObjects;
    }
}
