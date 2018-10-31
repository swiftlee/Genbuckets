package me.ufo.genbuckets.lang;

import me.ufo.genbuckets.Genbuckets;

public class Messages {

    public String GIVEN_BUCKET;
    public String NOT_ENOUGH_FOR_PURCHASE;
    public String NOT_ENOUGH_FOR_PLACEMENT;
    private Genbuckets INSTANCE = Genbuckets.getInstance();

    public void build() {
        GIVEN_BUCKET = INSTANCE.getConfig().getString("MESSAGES.given-bucket");
        NOT_ENOUGH_FOR_PURCHASE = INSTANCE.getConfig().getString("MESSAGES.not-enough-for-purchase");
        NOT_ENOUGH_FOR_PLACEMENT = INSTANCE.getConfig().getString("MESSAGES.not-enough-for-placement");
    }

}
