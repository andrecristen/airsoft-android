package com.ddm.airsoftorganize.response;

import java.util.HashMap;
import java.util.List;
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
        "regras",
        "custo",
        "url",
        "imagem",
        "campo",
        "times",
        "timeUsuarioInscrito"
})
public class EventDetailedResponse {

    @JsonProperty("id")
    private Integer id;
    @JsonProperty("nome")
    private String nome;
    @JsonProperty("dataInicio")
    private String dataInicio;
    @JsonProperty("dataFim")
    private String dataFim;
    @JsonProperty("regras")
    private String regras;
    @JsonProperty("custo")
    private String custo;
    @JsonProperty("url")
    private String url;
    @JsonProperty("imagem")
    private Object imagem;
    @JsonProperty("campo")
    private FieldResponse campo;
    @JsonProperty("times")
    private List<EventDetailedTeamResponse> times = null;
    @JsonProperty("timeUsuarioInscrito")
    private Object timeUsuarioInscrito;
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

    @JsonProperty("regras")
    public String getRegras() {
        return regras;
    }

    @JsonProperty("regras")
    public void setRegras(String regras) {
        this.regras = regras;
    }

    @JsonProperty("custo")
    public String getCusto() {
        return custo;
    }

    @JsonProperty("custo")
    public void setCusto(String custo) {
        this.custo = custo;
    }

    @JsonProperty("url")
    public String getUrl() {
        return url;
    }

    @JsonProperty("url")
    public void setUrl(String url) {
        this.url = url;
    }

    @JsonProperty("imagem")
    public Object getImagem() {
        return imagem;
    }

    @JsonProperty("imagem")
    public void setImagem(Object imagem) {
        this.imagem = imagem;
    }

    @JsonProperty("campo")
    public FieldResponse getCampo() {
        return campo;
    }

    @JsonProperty("campo")
    public void setCampo(FieldResponse campo) {
        this.campo = campo;
    }

    @JsonProperty("times")
    public List<EventDetailedTeamResponse> getTimes() {
        return times;
    }

    @JsonProperty("times")
    public void setTimes(List<EventDetailedTeamResponse> times) {
        this.times = times;
    }

    @JsonProperty("timeUsuarioInscrito")
    public Object getTimeUsuarioInscrito() {
        return timeUsuarioInscrito;
    }

    @JsonProperty("timeUsuarioInscrito")
    public void setTimeUsuarioInscrito(Object timeUsuarioInscrito) {
        this.timeUsuarioInscrito = timeUsuarioInscrito;
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