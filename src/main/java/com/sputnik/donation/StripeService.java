package com.sputnik.donation;

import com.sputnik.campaign.Campaign;
import com.sputnik.persistence.User;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;

@Service
public class StripeService {
    @Inject
    Environment env;

    public Charge createCharge(PendingDonation pendingDonation, User user, Campaign campaign) {
        Stripe.apiKey = env.getProperty("stripeApiKey");

        Map<String, Object> chargeMap = new HashMap<>();
        chargeMap.put("amount", pendingDonation.getDollarAmount());
        chargeMap.put("currency", "usd");
        chargeMap.put("receipt_email", user.getEmail());
        chargeMap.put("statement_description", campaign.getTitle());

        Map<String, Object> metadata = new HashMap<>();
        metadata.put("user_id", pendingDonation.getUserId());
        metadata.put("campaign_id", pendingDonation.getCampaignId());
        chargeMap.put("metadata", metadata);

        Map<String, Object> cardMap = new HashMap<>();
        cardMap.put("number", pendingDonation.getCardNumber());
        cardMap.put("exp_month", pendingDonation.getCardExpirationMonth());
        cardMap.put("exp_year", pendingDonation.getCardExpirationYear());
        chargeMap.put("card", cardMap);

        try {
            Charge charge = Charge.create(chargeMap);
            System.out.println(charge);
            return charge;
        } catch (StripeException e) {
            e.printStackTrace();
            return null;
        }
    }
}
