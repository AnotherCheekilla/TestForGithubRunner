## FIT5171 Week 9 Referral Tutorial Starter

This is the student practice project for the Week 9 applied session on object-oriented testing.

Start with:

- `src/main/java/clinic/service/ReferralService.java`
- `src/test/java/clinic/service/ReferralServiceTest.java`
- `src/test/java/clinic/policy/EligibilityPolicyContractTest.java`
- `src/test/java/clinic/policy/UrgentReferralPolicyTest.java`

Run:

```sh
mvn test
```

The initial run is expected to fail because `ReferralService.createReferral(...)` is unfinished. Use the tests and the Week 9 applied sheet to complete the class-level behaviour, then inspect how the contract tests exercise inheritance and dynamic dispatch risk.
