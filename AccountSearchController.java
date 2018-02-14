public class AccountSeachController {
    
    public List<Contact> contactsList { get; set; }
    public Account accountRecord { get; set; }
    public Contact contactRecord { get; set; }
    
    private List<String> searchCriteria = new List<String>();
    
    public AccountSeachController () {
        accountRecord = new Account();
        contactRecord = new Contact();
        contactsList  = [SELECT id, AccountId, Account.BillingCountry, Account.ShippingCountry, Account.Type, Account.CreatedDate, Name FROM Contact ORDER BY AccountId];
    }
    
    public void search() {
		seachAccounts();        
    }
    
    public void seachAccounts() {
        system.debug(accountRecord.Type);
        searchCriteria.clear();
        string accountName = '';
        if (accountRecord.Name != null) {
            if (accountRecord.Name.substring(0,1) == '*' && accountRecord.Name.substring(accountRecord.Name.length() - 1,accountRecord.Name.length()) == '*') {
                accountName = accountRecord.Name.substring(1,accountRecord.Name.length() - 1);
                searchCriteria.add('Account.Name LIKE \'%'+accountName+'%\'');
            } else if (accountRecord.Name.substring(0,1) == '*') {
                accountName = accountRecord.Name.substring(1,accountRecord.Name.length());
                searchCriteria.add('Account.Name LIKE \'%'+accountName +'\'');
            } else if (accountRecord.Name.substring(accountRecord.Name.length() - 1,accountRecord.Name.length()) == '*') {
                accountName = accountRecord.Name.substring(0,accountRecord.Name.length() - 1);
                searchCriteria.add('Account.Name LIKE '+'\''+accountName+'%\'');
            } else {
                accountName = accountRecord.Name.substring(1,accountRecord.Name.length() - 1);
                searchCriteria.add('Account.Name LIKE \'%'+accountName+'%\'');
            }
            
        }
        if (contactRecord.FirstName != null) {
            searchCriteria.add('Name LIKE \'%'+contactRecord.FirstName +'%\'');
        }
        if (accountRecord.BillingCountry != null) {
            searchCriteria.add('(Account.BillingCountry LIKE \'%'+accountRecord.BillingCountry +'%\''+' OR Account.ShippingCountry LIKE \'%' +accountRecord.BillingCountry+'%\')');
        }
        if (accountRecord.Type != null) {
            searchCriteria.add('Account.Type = ' + '\''+accountRecord.Type+'\'');
        }
        String query = 'SELECT id, AccountId, Account.BillingCountry, Account.ShippingCountry, Account.Type, Account.CreatedDate, Name FROM Contact';
    	
        if (searchCriteria.size() > 0) {
            query += ' WHERE ' + searchCriteria[0];
            for (Integer i=1; i<searchCriteria.size(); i++)
                query += ' AND ' + searchCriteria[i];
            
        }
        contactsList = Database.query(query+= ' ORDER BY AccountId');
    }

}