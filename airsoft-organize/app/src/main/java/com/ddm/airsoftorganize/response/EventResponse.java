package com.ddm.airsoftorganize.response;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "nome",
        "dataInicio",
        "dataFim",
        "campo"
})
public class EventResponse {

    @JsonProperty("id")
    private Integer id;
    @JsonProperty("nome")
    private String nome;
    @JsonProperty("dataInicio")
    private String dataInicio;
    @JsonProperty("dataFim")
    private String dataFim;
    @JsonProperty("campo")
    private FieldResponse campo;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("id")
    public Integer getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Integer id) {
        this.id = id;
    }

    @JsonProperty("nome")
    public String getNome() {
        return nome;
    }

    @JsonProperty("nome")
    public void setNome(String nome) {
        this.nome = nome;
    }

    @JsonProperty("dataInicio")
    public String getDataInicio() {
        return dataInicio;
    }

    @JsonProperty("dataInicio")
    public void setDataInicio(String dataInicio) {
        this.dataInicio = dataInicio;
    }

    @JsonProperty("dataFim")
    public String getDataFim() {
        return dataFim;
    }

    @JsonProperty("dataFim")
    public void setDataFim(String dataFim) {
        this.dataFim = dataFim;
    }

    @JsonProperty("campo")
    public FieldResponse getCampo() {
        return campo;
    }

    @JsonProperty("campo")
    public void setCampo(FieldResponse campo) {
        this.campo = campo;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
