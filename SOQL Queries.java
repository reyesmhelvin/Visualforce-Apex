//Account->Contacts
SELECT id, Name, BillingCountry, (SELECT id, Name FROM Contacts) FROM Account