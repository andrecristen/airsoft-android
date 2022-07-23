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
        "cor",
        "maximoOperadores",
        "minimoOperadores",
        "inscritos",
        "objetivo"
})
public class EventDetailedTeamResponse {

    @JsonProperty("id")
    private Integer id;
    @JsonProperty("nome")
    private String nome;
    @JsonProperty("cor")
    private Object cor;
    @JsonProperty("maximoOperadores")
    private Object maximoOperadores;
    @JsonProperty("minimoOperadores")
    private Object minimoOperadores;
    @JsonProperty("inscritos")
    private Integer inscritos;
    @JsonProperty("objetivo")
    private Object objetivo;
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

    @JsonProperty("cor")
    public Object getCor() {
        return cor;
    }

    @JsonProperty("cor")
    public void setCor(Object cor) {
        this.cor = cor;
    }

    @JsonProperty("maximoOperadores")
    public Object getMaximoOperadores() {
        return maximoOperadores;
    }

    @JsonProperty("maximoOperadores")
    public void setMaximoOperadores(Object maximoOperadores) {
        this.maximoOperadores = maximoOperadores;
    }

    @JsonProperty("minimoOperadores")
    public Object getMinimoOperadores() {
        return minimoOperadores;
    }

    @JsonProperty("minimoOperadores")
    public void setMinimoOperadores(Object minimoOperadores) {
        this.minimoOperadores = minimoOperadores;
    }

    @JsonProperty("inscritos")
    public Integer getInscritos() {
        return inscritos;
    }

    @JsonProperty("inscritos")
    public void setInscritos(Integer inscritos) {
        this.inscritos = inscritos;
    }

    @JsonProperty("objetivo")
    public Object getObjetivo() {
        return objetivo;
    }

    @JsonProperty("objetivo")
    public void setObjetivo(Object objetivo) {
        this.objetivo = objetivo;
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