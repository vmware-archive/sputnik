package com.sputnik.strava.segmenteffort;

import com.sputnik.strava.StravaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class SegmentEffortController {

    @Autowired
    private StravaService stravaService;

    @RequestMapping(method = RequestMethod.GET, value = "/strava/segment_efforts")
    public
    @ResponseBody
    Iterable<SegmentEffort> getAllSegmentEfforts() {
        return stravaService.getSegmentEfforts();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/strava/segment_efforts/{id}")
    public
    @ResponseBody
    SegmentEffort getSegmentEffortsById(@PathVariable String id) {
        return stravaService.getSegmentEffortById(id);
    }
}
