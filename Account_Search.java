<apex:page controller="AccountSeachController" sidebar="false">
    <apex:image value="{!$Resource.AccountSearch}" alt="Account Search"/>
    <apex:form >
        <apex:pageBlock title="Search Criteria" helpTitle="How To Search" helpUrl="How_To_Search"> 
            <apex:pageBlockSection columns="4">
                <apex:inputField value="{!accountRecord.Name}" required="false"/>
                <apex:inputField value="{!accountRecord.BillingCountry}"/>
                <apex:inputField value="{!contactRecord.FirstName}" required="false"/>
                <apex:inputField value="{!accountRecord.Type}"/>
                <apex:commandButton value="Find Accounts" action="{!search}"/>
            </apex:pageBlockSection>
        </apex:pageBlock>
        <apex:pageBlock title="Search Results"> 
            <apex:outputPanel rendered="{!IF(contactsList.size == 0, true, false)}">
            	<p><em>No matching records</em></p>
            </apex:outputPanel>
            <apex:pageBlockTable value="{!contactsList}" var="contactListItem" rendered="{!IF(contactsList.size > 0, true, false)}">
                <apex:column value="{!contactListItem.Accountid}" headerValue="Account Name"/>
                <apex:column value="{!contactListItem.Account.BillingCountry}" headerValue="Billing Country"/>
                <apex:column value="{!contactListItem.Account.ShippingCountry}" headerValue="Shipping Country"/>
                <apex:column value="{!contactListItem.Name}"/>
                <apex:column value="{!contactListItem.Account.Type}" headerValue="Type"/>
                <apex:column value="{!contactListItem.Account.CreatedDate}" headerValue="Year Created"/>
            </apex:pageBlockTable>
            <apex:pageblockButtons >
                <apex:commandButton value="<<" rerender="details" />
                <apex:commandButton value="<Previous" rerender="details"  />
                <apex:commandButton value="Next>" rerender="details"  />
                <apex:commandButton value=">>" rerender="details"/>
            </apex:pageblockButtons>
        </apex:pageBlock>
    </apex:form>
</apex:page>