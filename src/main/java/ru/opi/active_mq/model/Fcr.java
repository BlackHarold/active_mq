package ru.opi.active_mq.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Fcr {

    @JsonAlias("suidId")
    protected String suidId;

    @JsonAlias("imsId")
    protected String imsId;

    @JsonAlias("attachment")
    protected Attachment attachment;

    @JsonAlias("code")
    protected String code;

    @JsonAlias("systemRevision")
    protected String systemRevision;

    @JsonAlias("creator")
    protected String creator;

    @JsonAlias("state")
    protected String state;

    @JsonAlias("stateDate")
    protected String stateDate;

    @JsonAlias("type")
    protected String type;

    @JsonAlias("creationDate")
    protected String creationDate;

    @JsonAlias("changeInitiator")
    protected String changeInitiator;

    @JsonAlias("changeInitiatorOrganization")
    protected String changeInitiatorOrganization;

    @JsonAlias("FCRregistrationDate")
    protected String fcrRegistrationDate;

    @JsonAlias("summaryFieldChangeProposal")
    protected String summaryFieldChangeProposal;

    @JsonAlias("descriptionFieldChangeProposal")
    protected String descriptionFieldChangeProposal;

    @JsonAlias("justification")
    protected String justification;

    @JsonAlias("unavoidableChange")
    protected String unavoidableChange;

    @JsonAlias("proposedDeadline")
    protected String proposedDeadline;

    @JsonAlias("changeInitiatorName")
    protected String changeInitiatorName;

    @JsonAlias("justificationForCorrection")
    protected String justificationForCorrection;

    @JsonAlias("contractorChangeCoordinatorName")
    protected String contractorChangeCoordinatorName;

    @JsonAlias("justificationForAnnulment")
    protected String justificationForAnnulment;

    @JsonAlias("dateChangeInitiatorSignature")
    protected String dateChangeInitiatorSignature;

    @JsonAlias("applicabilityKKS")
    protected String applicabilityKKS;

    @JsonAlias("changeEvaluationDisposition")
    protected String changeEvaluationDisposition;

    @JsonAlias("justificationForDecision")
    protected String justificationForDecision;

    @JsonAlias("generalDesignerOrganization")
    protected String generalDesignerOrganization;

    @JsonAlias("generalDesignerName")
    protected String generalDesignerName;

    @JsonAlias("generalDesignerSignatureDate")
    protected String generalDesignerSignatureDate;

    @JsonAlias("impactOnConfiguration")
    protected String impactOnConfiguration;

    @JsonAlias("decision")
    protected String decision;

    @JsonAlias("justificationForRejection")
    protected String justificationForRejection;

    @JsonAlias("contractorChangeManagerName")
    protected String contractorChangeManagerName;

    @JsonAlias("contractorChangeManagerSignatureDate")
    protected String contractorChangeManagerSignatureDate;

    @JsonAlias("FCO")
    protected List<Fco> fcos;

    @JsonAlias("documentSet")
    protected List<DocumentSet> documentSets;

    @JsonAlias("KKS")
    protected List<KKS> kkss;

    private class Attachment {
        @JsonAlias("fileName")
        private String fileName;
        @JsonAlias("fileSize")
        private Integer fileSize;
        @JsonAlias("hash")
        private String hash;
        @JsonAlias("url")
        private String url;
    }

    private class Fco {
        @JsonAlias("fcoId")
        private String fcoId;
    }

    private class DocumentSet {
        @JsonAlias("codeDS")
        private String codeDS;
        @JsonAlias("ims3IdDS")
        private String ims3IdDS;
        @JsonAlias("suidIdDS")
        private String suidIdDS;
        @JsonAlias("nameDS")
        private String nameDS;
        @JsonAlias("revisionDS")
        private String revisionDS;
        @JsonAlias("ChangeDescription")
        private ChangeDescription changeDescription;

        private class ChangeDescription {
            @JsonAlias("nameCD")
            private String nameCD;
            @JsonAlias("descriptionCD")
            private String descriptionCD;
            @JsonAlias("ownerCD")
            private String ownerCD;
            @JsonAlias("organizationCD")
            private String organizationCD;
            @JsonAlias("pageCD")
            private String pageCD;
            @JsonAlias("isAffectedCD")
            private String isAffectedCD;
            @JsonAlias("initiatorOrganizationCD")
            private String initiatorOrganizationCD;
            @JsonAlias("engDocumentCD")
            private String engDocumentCD;
        }
    }

    private class KKS {
        @JsonAlias("structureKKSCode")
        private String structureKKSCode;
        @JsonAlias("systemKKSCode")
        private String systemKKSCode;
        @JsonAlias("componentKKSCode")
        private String componentKKSCode;
        @JsonAlias("componentQACategory")
        private String componentQACategory;
        @JsonAlias("componentSafetyClass")
        private String componentSafetyClass;
        @JsonAlias("unavoidableChange")
        private String unavoidableChange;
    }
}
