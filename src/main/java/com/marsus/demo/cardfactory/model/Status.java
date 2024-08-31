package com.marsus.demo.cardfactory.model;

/**
 * All possible statuses of a request for new credit card
 */
public enum Status {

    /** New request for a credit card, awaiting approval */
    NEW,

    /** Credit card request has been approved, new credit is going to be created */
    APPROVED,

    /** Credit card request has been rejected, credit card won't be created */
    REJECTED,

    /** Credit card creation is in progress  */
    PENDING,

    /** Credit card request has been fulfilled, new credit card is ready, awaiting a client to pick it up */
    READY,

    /** Client has picked up the new credit card, process is completed */
    COMPLETED
}
