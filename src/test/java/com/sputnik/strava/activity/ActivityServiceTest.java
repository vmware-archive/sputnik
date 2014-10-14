package com.sputnik.strava.activity;

import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.social.strava.api.ActivityOperations;
import org.springframework.social.strava.api.Strava;
import org.springframework.social.strava.api.StravaActivity;

import java.util.List;

import static java.util.Arrays.asList;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

@RunWith(MockitoJUnitRunner.class)
public class ActivityServiceTest extends TestCase {
    @Mock
    Strava strava;

    @Mock
    ActivityOperations activityOperations;

    @Mock
    ActivityConverter activityConverter;

    @InjectMocks
    ActivityService activityService;

    @Test
    public void testGetActivities() throws Exception {
        List<Activity> activities = asList(mock(Activity.class));
        List<StravaActivity> stravaActivities = asList(mock(StravaActivity.class));

        doReturn(activityOperations).when(strava).activityOperations();
        doReturn(stravaActivities).when(activityOperations).getAllActivities(10);
        doReturn(activities).when(activityConverter).convertList(stravaActivities);

        List<Activity> allActivities = activityService.getActivities();

        assertEquals(activities, allActivities);
    }

    @Test
    public void testGetActivityById() throws Exception {
        Activity activity = mock(Activity.class);
        StravaActivity stravaActivity = mock(StravaActivity.class);

        doReturn(activityOperations).when(strava).activityOperations();
        doReturn(stravaActivity).when(activityOperations).getActivityById("7");
        doReturn(activity).when(activityConverter).convert(stravaActivity);

        Activity returnedActivity = activityService.getActivityById("7");

        assertEquals(activity, returnedActivity);
    }
}