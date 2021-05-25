package controller;

import java.time.LocalDate;

public class Reserva {
    private LocalDate DataFesta;
    private LocalDate DataPrevista;
    private LocalDate DataRetorno;
    private String HoraInicio;
    private String HoraPrevisto;
    private String HoraRetorno;
    private String FormaDePagamento;
    private double PrecoFinal;

    private String Cliente;
    private int QtdeAluguel;
    private String Enfeite;

    public Reserva( LocalDate DataFesta, LocalDate DataPrevista, 
    LocalDate DataRetorno, String HoraInicio, String HoraPrevisto, String HoraRetorno, 
    String FormaDePagamento, double PrecoFinal, int QtdeAluguel, String Cliente, String Enfeite ) {
        this.DataFesta = DataFesta;
        this.DataPrevista = DataPrevista;
        this.DataRetorno = DataRetorno;
        this.HoraInicio = HoraInicio;
        this.HoraPrevisto = HoraPrevisto;
        this.HoraRetorno = HoraRetorno;
        this.FormaDePagamento = FormaDePagamento;
        this.PrecoFinal = PrecoFinal;
        this.Cliente = Cliente;
        this.QtdeAluguel = QtdeAluguel;
        this.Enfeite = Enfeite;
    }

    public int getQtdeAluguel() {
        return QtdeAluguel;
    }

    public void setQtdeAluguel(int qtdeAluguel) {
        QtdeAluguel = qtdeAluguel;
    }

    public String getCliente() {
        return Cliente;
    }

    public void setCliente(String cliente) {
        Cliente = cliente;
    }

    public String getEnfeite() {
        return Enfeite;
    }

    public void setEnfeite(String enfeite) {
        Enfeite = enfeite;
    }

    public LocalDate getDataFesta() {
        return DataFesta;
    }

    public void setDataFesta(LocalDate dataFesta) {
        DataFesta = dataFesta;
    }

    public LocalDate getDataPrevista() {
        return DataPrevista;
    }

    public void setDataPrevista(LocalDate dataPrevista) {
        DataPrevista = dataPrevista;
    }

    public LocalDate getDataRetorno() {
        return DataRetorno;
    }

    public void setDataRetorno(LocalDate dataRetorno) {
        DataRetorno = dataRetorno;
    }
    
    public String getHoraInicio() {
        return HoraInicio;
    }

    public void setHoraInicio(String horaInicio) {
        HoraInicio = horaInicio;
    }

    public String getHoraRetorno() {
        return HoraRetorno;
    }

    public void setHoraRetorno(String horaRetorno) {
        HoraRetorno = horaRetorno;
    }

    public String getHoraPrevisto() {
        return HoraPrevisto;
    }

    public void setHoraPrevisto(String horaPrevisto) {
        HoraPrevisto = horaPrevisto;
    }

    public String getFormaDePagamento() {
        return FormaDePagamento;
    }

    public void setFormaDePagamento(String formaDePagamento) {
        FormaDePagamento = formaDePagamento;
    }

    public double getPrecoFinal() {
        return PrecoFinal;
    }

    public void setPrecoFinal(double precoFinal) {
        PrecoFinal = precoFinal;
    }
}