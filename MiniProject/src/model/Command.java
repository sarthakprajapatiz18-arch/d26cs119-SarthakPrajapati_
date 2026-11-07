package model;
// Purpose: Immutable transaction command data used by the command parser.
public record Command(TransactionType type, String accountNumber, long amount) { }
