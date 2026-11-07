# MiniBank Test Report

## Environment
- Java compiler/runtime available in the execution environment
- Compile flags: `javac -Xlint:all`
- No external libraries required for the standalone regression test

## Results

| Test | Result |
|---|---|
| Compile all MiniProject source files | PASS |
| Compile with `-Xlint:all` without warnings | PASS |
| Open Savings account | PASS |
| Open Current account | PASS |
| Deposit | PASS |
| Withdrawal | PASS |
| Transfer between accounts | PASS |
| Account statement | PASS |
| List accounts | PASS |
| Generate end-of-day report | PASS |
| Reject insufficient-funds withdrawal | PASS |
| Reject negative deposit | PASS |
| Report unknown account | PASS |
| Find inherited `@Id` field in `Account` | PASS |
| Parse command with repeated whitespace | PASS |
| Validate inherited annotated fields | PASS |
| Apply current-account overdraft limit | PASS |
| Reject fixed-deposit withdrawal | PASS |
| Roll back source debit if destination credit overflows | PASS |
| Serialize and deserialize account array | PASS |
| Serialize customer with nested address | PASS |

## Console workflow test
Created:
- `AC0001` — Uday, Savings, opening balance ₹5,000
- `AC0002` — Amit, Current, opening balance ₹1,000

Transactions:
- Deposit ₹500 to `AC0001`
- Withdraw ₹300 from `AC0001`
- Transfer ₹700 from `AC0001` to `AC0002`

Verified final balances:
- `AC0001`: ₹4,500
- `AC0002`: ₹1,700

Expected validation errors were also checked for insufficient funds, negative deposits and an unknown account number.

## Final result

**PASS — compilation and all included regression/workflow tests completed successfully.**

## Known design limitations
- Account data is saved on exit but is not automatically reloaded at startup.
- Fixed-deposit accounts cannot be withdrawn from in this implementation.
- The report totals deposits and withdrawals; transfer rows are logged but not included in those two totals.
- This is an academic demonstration and is not intended for real financial use.
