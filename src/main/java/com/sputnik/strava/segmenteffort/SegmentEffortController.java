package com.sputnik.strava.segmenteffort;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SegmentEffortController {

    @Autowired
    private SegmentEffortService segmentEffortService;

    @RequestMapping(method = RequestMethod.GET, value = "/strava/segment_efforts")
    public
    @ResponseBody
    Iterable<SegmentEffort> getAllSegmentEfforts() {
        return segmentEffortService.getSegmentEfforts();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/strava/segment_efforts/{id}")
    public
    @ResponseBody
    SegmentEffort getSegmentEffortsById(@PathVariable String id) {
        return segmentEffortService.getSegmentEffortById(id);
    }
}
