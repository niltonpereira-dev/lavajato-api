
package br.com.niltonpereira.lavajato_api.model.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "atendimentos")
public class Atendimento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "veiculo_id", nullable = false)
    private Veiculo veiculo;

    @Column(nullable = false)
    private LocalDateTime dataAtendimento;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal valorTotal = BigDecimal.ZERO;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private StatusAtendimento status = StatusAtendimento.AGUARDANDO;

    @OneToMany(
            mappedBy = "atendimento",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<ItemAtendimento> itens = new ArrayList<>();

    @PrePersist
    public void prePersist() {
        if (dataAtendimento == null) {
            dataAtendimento = LocalDateTime.now();
        }

        if (status == null) {
            status = StatusAtendimento.AGUARDANDO;
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    public LocalDateTime getDataAtendimento() {
        return dataAtendimento;
    }

    public void setDataAtendimento(LocalDateTime dataAtendimento) {
        this.dataAtendimento = dataAtendimento;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public StatusAtendimento getStatus() {
        return status;
    }

    public void setStatus(StatusAtendimento status) {
        this.status = status;
    }

    public List<ItemAtendimento> getItens() {
        return itens;
    }

    public void setItens(List<ItemAtendimento> itens) {
        this.itens.clear();

        if (itens != null) {
            for (ItemAtendimento item : itens) {
                item.setAtendimento(this);
                this.itens.add(item);
            }
        }
    }
}

