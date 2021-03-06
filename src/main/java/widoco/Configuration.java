/*
 * Copyright 2012-2013 Ontology Engineering Group, Universidad Politécnica de Madrid, Spain
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package widoco;

import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntResource;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.util.FileManager;
import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Properties;
import javax.imageio.ImageIO;
import widoco.entities.Agent;
import widoco.entities.License;
import widoco.entities.Ontology;
import widoco.gui.GuiController;

/**
 * class for storing all the details to generate the ontology.
 * This will be a singleton object that will be modified until the generate 
 * command is given.
 * 
 * @author Daniel Garijo
 */
public class Configuration {
    private ArrayList<Agent> creators;
    private ArrayList<Agent> contributors;
    private String previousVersion;
    private String thisVersion;
    private String latestVersion;
    private String revision;
    private ArrayList<Ontology> importedOntologies;
    private ArrayList<Ontology> extendedOntologies;
    private Ontology mainOntology;
    private License license;
    private String ontologyPath;
    private String documentationURI;
    private String title;
    private String releaseDate;
    
    private boolean fromFile;//if this is true, the onto will be from a file. otherwise it's a URI
    
    private boolean publishProvenance;
    private String provenanceURI; //this will be used as the subject for describing provenance (url of the doc)
    
    private boolean includeAbstract;
    private boolean includeIntroduction;
    private boolean includeOverview;
    private boolean includeDescription;
    private boolean includeReferences;
    private boolean includeCrossReferenceSection;//needed for skeleton
    private String abstractPath;
    private String introductionPath;
    private String overviewPath;
    private String descriptionPath;
    private String referencesPath;
    
    private boolean includeDiagram;
    
    private Properties propertyFile = null;
    
    
    //Lode configuration parameters
    private boolean useOwlAPI;
    private boolean useImported;
    private boolean useReasoner;
    private String language;
    
    private Image logo;
    private Image logoMini;
    
//    to do. Load from configuration file. Setters and getters to do it from the interface.
    //model everything as a singleton object. No need: only the controller accesses this file.
    public Configuration() {
        propertyFile = new Properties();
        //just in case, we initialize the objects:
        
        try{
            URL root = GuiController.class.getProtectionDomain().getCodeSource().getLocation();
            String path = (new File(root.toURI())).getParentFile().getPath();
            loadPropertyFile(path+File.separator+TextConstants.configPath);
        }catch(URISyntaxException e){
            System.err. println("Error while loading the default property file" +e.getMessage());
        }
    }
    
    private void cleanConfig(){
        //initialization of variables (in case something fails
        title ="";
        releaseDate = "";
        previousVersion ="";
        thisVersion ="";
        latestVersion ="";
        revision = "";
        creators = new ArrayList<Agent>();
        contributors = new ArrayList<Agent>();
        importedOntologies = new ArrayList<Ontology>();
        extendedOntologies = new ArrayList<Ontology>();
        //this has to be checked because we might delete the uri of the onto from a previous step.
        if(mainOntology==null){
                mainOntology = new Ontology();
                mainOntology.setName("");
                mainOntology.setNamespacePrefix("");
                mainOntology.setNamespaceURI("");
        }
        license = new License();
        publishProvenance = true;    
        includeAbstract = true;
        includeIntroduction = true;
        includeOverview = true;
        includeDescription = true;
        includeReferences = true;
        includeCrossReferenceSection = true;
    }
    
    private void loadPropertyFile(String path){
        try {
            cleanConfig();
            
//            propertyFile.load(new FileInputStream(path));
            //this forces the property file to be in UTF 8 instead of the ISO
            propertyFile.load(new InputStreamReader(new FileInputStream(path), "UTF-8"));
            //We try to load from the configuration file. If it fails, then we should try to load from the ontology. Then, if it fails, we should ask the user.
            this.title = propertyFile.getProperty("title","Title goes here");
            this.releaseDate = propertyFile.getProperty("dateOfRelease", "Date of release");
            this.previousVersion =propertyFile.getProperty("previousVersion");
            thisVersion =propertyFile.getProperty("thisVersion");
            latestVersion =propertyFile.getProperty("latestVersion");
            mainOntology.setName(propertyFile.getProperty("name"));
            mainOntology.setNamespacePrefix(propertyFile.getProperty("ontologyPrefix"));
            mainOntology.setNamespaceURI(propertyFile.getProperty("ontologyNamespaceURI"));
            revision = propertyFile.getProperty("revision");
            //to do: check that the authors is not empty before doing the split.
            String[] names = propertyFile.getProperty("authors").split(";");
            String[] urls = propertyFile.getProperty("authorURL").split(";");
            String[] authorInst = propertyFile.getProperty("authorInstitution").split(";");
            for(int i =0; i< names.length; i++){
                Agent a = new Agent();
                a.setName(names[i]);
                if(urls.length == names.length){
                    a.setURL(urls[i]);
                }
                if(authorInst.length == names.length){
                    a.setInstitutionName(authorInst[i]);
                }
                creators.add(a);
            }
            names = propertyFile.getProperty("contributors").split(";");
            urls = propertyFile.getProperty("contributorsURL").split(";");
            authorInst = propertyFile.getProperty("contributorsInstitution").split(";");
            for(int i =0; i< names.length; i++){
                Agent a = new Agent();
                a.setName(names[i]);
                if(urls.length == names.length){
                    a.setURL(urls[i]);
                }
                if(authorInst.length == names.length){
                    a.setInstitutionName(authorInst[i]);
                }
                contributors.add(a);
            }
            names = propertyFile.getProperty("importsNames").split(";");
            urls = propertyFile.getProperty("importsURLs").split(";");
            for(int i =0; i< names.length; i++){
                Ontology o = new Ontology();
                o.setName(names[i]);
                if(urls.length == names.length){
                    o.setNamespaceURI(urls[i]);
                }
                importedOntologies.add(o);
            }
            names = propertyFile.getProperty("extendsNames").split(";");
            urls = propertyFile.getProperty("extendsURLS").split(";");
            for(int i =0; i< names.length; i++){
                Ontology o = new Ontology();
                o.setName(names[i]);
                if(urls.length == names.length){
                    o.setNamespaceURI(urls[i]);
                }
                extendedOntologies.add(o);
            }
            license.setName(propertyFile.getProperty("license"));
            license.setUrl(propertyFile.getProperty("licenseURL"));
            license.setIcon(propertyFile.getProperty("licenseIconURL"));
            //to do: add the license icon!
    	} catch (IOException ex) {
            System.err.println("Error while reading configuration properties "+ex.getMessage());
        }
    }
    
    public void loadPropertiesFromOntology(OntModel m){
        if(m == null){
            System.err.println("The ontology could not be read...");
            return;
        }
        cleanConfig();
        //we assume only one ontology per file.
        OntResource onto = m.getOntClass("http://www.w3.org/2002/07/owl#Ontology").listInstances().next();
        Iterator it = onto.listProperties();//model.getResource("http://purl.org/net/wf-motifs").listProperties();
        String propertyName, value;
        while(it.hasNext()){
            Statement s = (Statement) it.next();
            propertyName = s.getPredicate().getLocalName();
            try{
                value = s.getObject().asLiteral().getString();
            }catch(Exception e){
                value = s.getObject().asResource().getURI();
            }
//            System.out.println(propertyName + " " + value);
            // fill in the properties here.
            if(propertyName.equals("abstract")){
                // to do: create a field named abstract in the config class
            }else
            if(propertyName.equals("title")){
                this.title = value;
            }else
            if(propertyName.equals("replaces")||propertyName.equals("wasRevisionOf")){
                this.previousVersion = value;
            }else
            if(propertyName.equals("versionInfo")){
                this.revision = value;
            }else
            if(propertyName.equals("preferredNamespacePrefix")){
                this.mainOntology.setNamespacePrefix(value);
            }else
            if(propertyName.equals("preferredNamespaceUri")){
                this.mainOntology.setNamespaceURI(value);                
            }else
            if(propertyName.equals("license")){
                this.license = new License();
                if(isURL(value)){
                    this.license.setUrl(value);
                }else{
                    license.setName(value);
                }
            }else
            if(propertyName.equals("creator")||propertyName.equals("contributor")){
                Agent g = new Agent();
                if(isURL(value)){
                    g.setURL(value);
                    g.setName("name goes here");
                }else{
                    g.setName(value);
                    g.setURL("url oges here");
                }
                if(propertyName.equals("creator")){
                    this.creators.add(g);
                }else{
                    this.contributors.add(g);
                }
            }else
            if(propertyName.equals("created")){
                if(releaseDate==null || "".equals(releaseDate)){
                    this.releaseDate = value;
                }
            }else
            if(propertyName.equals("modified")){
                releaseDate = value;
            }else
            if(propertyName.equals("imports")){
                Ontology o = new Ontology();
                if(isURL(value)){
                    o.setNamespaceURI(value);
                    o.setName("imported ontology name goes here");
                }else{
                    o.setName(value);
                    o.setNamespaceURI("namespace URI goes here");
                }
                this.importedOntologies.add(o);
            }
            //to do: if property is comment and abstract is null, then complete abstract.
        }
        System.out.println("Loaded properties from ontology");
    }
    
    private boolean isURL(String s){
        try{
            URL url = new URL(s);
            url.toURI();
            return true;
        }catch(MalformedURLException e){
            return false;
        } catch (URISyntaxException e) {
            return false;
        }
    }

    public boolean isFromFile() {
        return fromFile;
    }
    
    public void reloadPropertyFile(String path){
        this.loadPropertyFile(path);
    }

    public ArrayList<Agent> getContributors() {
        return contributors;
    }

    public ArrayList<Agent> getCreators() {
        return creators;
    }

    public String getDocumentationURI() {
        return documentationURI;
    }

    public ArrayList<Ontology> getExtendedOntologies() {
        return extendedOntologies;
    }

    public String getRevision() {
        return revision;
    }

    public ArrayList<Ontology> getImportedOntolgies() {
        return importedOntologies;
    }

    public String getLatestVersion() {
        return latestVersion;
    }

    public License getLicense() {
        return license;
    }

    public Ontology getMainOntology() {
        return mainOntology;
    }

    public String getOntologyPath() {
        return ontologyPath;
    }

    public String getOntologyURI() {
        return this.mainOntology.getNamespaceURI();
    }

    public String getPreviousVersion() {
        return previousVersion;
    }

    public String getThisVersion() {
        return thisVersion;
    }

    public String getTitle() {
        return title;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public boolean isPublishProvenance() {
        return publishProvenance;
    }

    public String getProvenanceURI() {
        return provenanceURI;
    }

    
    
    public void setProvenanceURI(String provenanceURI) {
        this.provenanceURI = provenanceURI;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public void setTitle(String title) {
        if(title==null) this.title= "Untitled ontology";
        else this.title = title;
    }


    public void setContributors(ArrayList<Agent> contributors) {
        this.contributors = contributors;
    }

    public void setCreators(ArrayList<Agent> creators) {
        this.creators = creators;
    }

    public void setDocumentationURI(String documentationURI) {
        this.documentationURI = documentationURI;
    }

    public void setExtendedOntologies(ArrayList<Ontology> extendedOntologies) {
        this.extendedOntologies = extendedOntologies;
    }

    public void setGetRevision(String getRevision) {
        this.revision = getRevision;
    }

    public void setImportedOntolgies(ArrayList<Ontology> importedOntolgies) {
        this.importedOntologies = importedOntolgies;
    }

    public void setLatestVersion(String latestVersion) {
        this.latestVersion = latestVersion;
    }

    public void setLicense(License license) {
        this.license = license;
    }

    public void setMainOntology(Ontology mainOntology) {
        this.mainOntology = mainOntology;
    }

    public void setOntologyPath(String ontologyPath) {
        this.ontologyPath = ontologyPath;
    }

    public void setOntologyURI(String ontologyURI) {
        this.mainOntology.setNamespaceURI(ontologyURI);
    }

    public void setPreviousVersion(String previousVersion) {
        this.previousVersion = previousVersion;
    }

    public void setThisVersion(String thisVersion) {
        this.thisVersion = thisVersion;
    }

    public void setPublishProvenance(boolean publishProvenance) {
        this.publishProvenance = publishProvenance;
    }

    public String getAbstractPath() {
        return abstractPath;
    }

    public String getDescriptionPath() {
        return descriptionPath;
    }

    public String getIntroductionPath() {
        return introductionPath;
    }

    public ArrayList<Ontology> getImportedOntologies() {
        return importedOntologies;
    }

    public String getOverviewPath() {
        return overviewPath;
    }

    public String getReferencesPath() {
        return referencesPath;
    }

    public boolean isIncludeAbstract() {
        return includeAbstract;
    }

    public boolean isIncludeDescription() {
        return includeDescription;
    }

    public boolean isIncludeDiagram() {
        return includeDiagram;
    }

    public boolean isIncludeIntroduction() {
        return includeIntroduction;
    }

    public boolean isIncludeOverview() {
        return includeOverview;
    }

    public boolean isIncludeReferences() {
        return includeReferences;
    }

    public boolean isIncludeCrossReferenceSection() {
        return includeCrossReferenceSection;
    }

    public void setAbstractPath(String abstractPath) {
        this.abstractPath = abstractPath;
    }

    public void setDescriptionPath(String descriptionPath) {
        this.descriptionPath = descriptionPath;
    }

    public void setIncludeAbstract(boolean includeAbstract) {
        this.includeAbstract = includeAbstract;
    }

    public void setImportedOntologies(ArrayList<Ontology> importedOntologies) {
        this.importedOntologies = importedOntologies;
    }

    public void setIncludeDescription(boolean includeDescription) {
        this.includeDescription = includeDescription;
    }

    public void setIncludeDiagram(boolean includeDiagram) {
        this.includeDiagram = includeDiagram;
    }

    public void setIncludeIntroduction(boolean includeIntroduction) {
        this.includeIntroduction = includeIntroduction;
    }

    public void setIncludeOverview(boolean includeOverview) {
        this.includeOverview = includeOverview;
    }

    public void setIncludeReferences(boolean includeReferences) {
        this.includeReferences = includeReferences;
    }

    public void setIncludeCrossReferenceSection(boolean includeCrossReferenceSection) {
        this.includeCrossReferenceSection = includeCrossReferenceSection;
    }

    public void setIntroductionPath(String introductionPath) {
        this.introductionPath = introductionPath;
    }

    public void setOverviewPath(String overviewPath) {
        this.overviewPath = overviewPath;
    }

    public void setPropertyFile(Properties propertyFile) {
        this.propertyFile = propertyFile;
    }

    public void setReferencesPath(String referencesPath) {
        this.referencesPath = referencesPath;
    }

    public void setRevision(String revision) {
        this.revision = revision;
    }    
    
    public void setFromFile(boolean fromFile) {
        this.fromFile = fromFile;
    }
    
    /**
     * Lode configuration parameters
     */
    
    public String getLanguage() {
        return language;
    }

    public boolean isUseImported() {
        return useImported;
    }

    public boolean isUseOwlAPI() {
        return useOwlAPI;
    }

    public boolean isUseReasoner() {
        return useReasoner;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public void setUseOwlAPI(boolean useOwlAPI) {
        this.useOwlAPI = useOwlAPI;
    }

    public void setUseImported(boolean useImported) {
        this.useImported = useImported;
    }

    public void setUseReasoner(boolean useReasoner) {
        this.useReasoner = useReasoner;
    }
    
    public Image getLogo(){
        if(logo == null){
            loadLogos();
        }
        return this.logo;
    }
    
    public Image getLogoMini(){
        if(logoMini == null){
            loadLogos();
        }
        return this.logoMini;
    }
    
    private void loadLogos(){
        try {
            //logo
            this.logo = ImageIO.read(ClassLoader.getSystemResource("logo/logo2.png"));
            this.logoMini = ImageIO.read(ClassLoader.getSystemResource("logo/logomini100.png"));
        } catch (IOException e) {
            System.err.println("Error loading the logo :( "+e.getMessage());
        }
    }

    
    //for testing ontology readings.
//    public static void main(String[] args){
//      Configuration c = new Configuration();
//      c.loadPropertiesFromOntology(null);
//      Iterator it = c.getContributors().iterator();
//      while(it.hasNext()){
//        System.out.println(((Agent)it.next()).getURL());
//      }
//    }
    
}
