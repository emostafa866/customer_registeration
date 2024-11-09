package userFederation;

import com.clients.Customer;
import com.clients.CustomerClient;
import org.keycloak.component.ComponentModel;
import org.keycloak.credential.CredentialInput;
import org.keycloak.credential.CredentialInputValidator;
import org.keycloak.credential.CredentialModel;
import org.keycloak.models.*;
import org.keycloak.storage.UserStorageProvider;
import org.keycloak.storage.user.UserLookupProvider;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


public class CustomUserFederationProvider implements UserStorageProvider, UserLookupProvider, CredentialInputValidator {

    private final KeycloakSession session;
    private final ComponentModel model;
    private final BCryptPasswordEncoder passwordEncoder;
    private final CustomerClient customerClient;

    public CustomUserFederationProvider(KeycloakSession session, ComponentModel model, CustomerClient customerClient) {
        this.session = session;
        this.model = model;
        this.customerClient = customerClient;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    @Override
    public UserModel getUserById(RealmModel realm, String id) {
        return UserLookupProvider.super.getUserById(realm, id);
    }

    @Override
    public UserModel getUserById(String id, RealmModel realm) {
        return getUserByUsername(id, realm);
    }

    @Override
    public UserModel getUserByUsername(String username, RealmModel realmModel) {
        Customer customer = customerClient.getCustomerByUsername(username);
        if (customer == null) return null;

        return new CustomUserAdapter(session, realmModel, model, customer);
    }


    @Override
    public UserModel getUserByEmail(String email, RealmModel realmModel) {
        return UserLookupProvider.super.getUserByEmail(realmModel, email);

    }

    @Override
    public boolean supportsCredentialType(String credentialType) {
        return credentialType.equals(CredentialModel.PASSWORD);
    }

    @Override
    public boolean isConfiguredFor(RealmModel realm, UserModel user, String credentialType) {
        return supportsCredentialType(credentialType);
    }

    @Override
    public boolean isValid(RealmModel realm, UserModel user, CredentialInput credentialInput) {
        if (!(credentialInput instanceof UserCredentialModel)) return false;
        String rawPassword = credentialInput.getChallengeResponse();
        Customer customer = customerClient.getCustomerByUsername(user.getEmail());

        return customer != null && passwordEncoder.matches(rawPassword, customer.getPassword());
    }

    @Override
    public void close() {
        // Close resources if any
    }

    @Override
    public void preRemove(RealmModel realm) {
        UserStorageProvider.super.preRemove(realm);
    }

    @Override
    public void preRemove(RealmModel realm, GroupModel group) {
        UserStorageProvider.super.preRemove(realm, group);
    }

    @Override
    public void preRemove(RealmModel realm, RoleModel role) {
        UserStorageProvider.super.preRemove(realm, role);
    }
}
