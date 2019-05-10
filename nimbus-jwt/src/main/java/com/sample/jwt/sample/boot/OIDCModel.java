package com.sample.jwt.sample.boot;

import java.util.ArrayList;
import java.util.List;

public class OIDCModel {
 private String issuer;
 private String authorization_endpoint;
 private String token_endpoint;
 private List < String > token_endpoint_auth_methods_supported = new ArrayList < String > ();
 private ArrayList < String > token_endpoint_auth_signing_alg_values_supported = new ArrayList < String > ();
 private String userinfo_endpoint;
 private String jwks_uri;
 ArrayList < String > scopes_supported = new ArrayList < String > ();
 ArrayList < String > response_types_supported = new ArrayList < String > ();
 ArrayList < String > subject_types_supported = new ArrayList < String > ();
 ArrayList < String > id_token_signing_alg_values_supported = new ArrayList < String > ();
 ArrayList < String > id_token_encryption_alg_values_supported = new ArrayList < String > ();
 ArrayList < String > claim_types_supported = new ArrayList < String > ();
 ArrayList < String > claims_supported = new ArrayList < String > ();
 private boolean claims_parameter_supported;
 private String service_documentation;
 ArrayList < String > ui_locales_supported = new ArrayList < String > ();
public String getIssuer() {
	return issuer;
}
public void setIssuer(String issuer) {
	this.issuer = issuer;
}
public String getAuthorization_endpoint() {
	return authorization_endpoint;
}
public void setAuthorization_endpoint(String authorization_endpoint) {
	this.authorization_endpoint = authorization_endpoint;
}
public String getToken_endpoint() {
	return token_endpoint;
}
public void setToken_endpoint(String token_endpoint) {
	this.token_endpoint = token_endpoint;
}
public List<String> getToken_endpoint_auth_methods_supported() {
	return token_endpoint_auth_methods_supported;
}
public void setToken_endpoint_auth_methods_supported(List<String> token_endpoint_auth_methods_supported) {
	this.token_endpoint_auth_methods_supported = token_endpoint_auth_methods_supported;
}
public ArrayList<String> getToken_endpoint_auth_signing_alg_values_supported() {
	return token_endpoint_auth_signing_alg_values_supported;
}
public void setToken_endpoint_auth_signing_alg_values_supported(
		ArrayList<String> token_endpoint_auth_signing_alg_values_supported) {
	this.token_endpoint_auth_signing_alg_values_supported = token_endpoint_auth_signing_alg_values_supported;
}
public String getUserinfo_endpoint() {
	return userinfo_endpoint;
}
public void setUserinfo_endpoint(String userinfo_endpoint) {
	this.userinfo_endpoint = userinfo_endpoint;
}
public String getJwks_uri() {
	return jwks_uri;
}
public void setJwks_uri(String jwks_uri) {
	this.jwks_uri = jwks_uri;
}
public ArrayList<String> getScopes_supported() {
	return scopes_supported;
}
public void setScopes_supported(ArrayList<String> scopes_supported) {
	this.scopes_supported = scopes_supported;
}
public ArrayList<String> getResponse_types_supported() {
	return response_types_supported;
}
public void setResponse_types_supported(ArrayList<String> response_types_supported) {
	this.response_types_supported = response_types_supported;
}
public ArrayList<String> getSubject_types_supported() {
	return subject_types_supported;
}
public void setSubject_types_supported(ArrayList<String> subject_types_supported) {
	this.subject_types_supported = subject_types_supported;
}
public ArrayList<String> getId_token_signing_alg_values_supported() {
	return id_token_signing_alg_values_supported;
}
public void setId_token_signing_alg_values_supported(ArrayList<String> id_token_signing_alg_values_supported) {
	this.id_token_signing_alg_values_supported = id_token_signing_alg_values_supported;
}
public ArrayList<String> getId_token_encryption_alg_values_supported() {
	return id_token_encryption_alg_values_supported;
}
public void setId_token_encryption_alg_values_supported(ArrayList<String> id_token_encryption_alg_values_supported) {
	this.id_token_encryption_alg_values_supported = id_token_encryption_alg_values_supported;
}
public ArrayList<String> getClaim_types_supported() {
	return claim_types_supported;
}
public void setClaim_types_supported(ArrayList<String> claim_types_supported) {
	this.claim_types_supported = claim_types_supported;
}
public ArrayList<String> getClaims_supported() {
	return claims_supported;
}
public void setClaims_supported(ArrayList<String> claims_supported) {
	this.claims_supported = claims_supported;
}
public boolean isClaims_parameter_supported() {
	return claims_parameter_supported;
}
public void setClaims_parameter_supported(boolean claims_parameter_supported) {
	this.claims_parameter_supported = claims_parameter_supported;
}
public String getService_documentation() {
	return service_documentation;
}
public void setService_documentation(String service_documentation) {
	this.service_documentation = service_documentation;
}
public ArrayList<String> getUi_locales_supported() {
	return ui_locales_supported;
}
public void setUi_locales_supported(ArrayList<String> ui_locales_supported) {
	this.ui_locales_supported = ui_locales_supported;
}


 
}