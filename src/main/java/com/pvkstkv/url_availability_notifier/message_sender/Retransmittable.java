package com.pvkstkv.url_availability_notifier.message_sender;

public interface Retransmittable {
    void retransmit(String text);
}
