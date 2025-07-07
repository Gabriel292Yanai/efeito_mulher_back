package com.example.efeito_mulher.service;

import com.example.efeito_mulher.dto.BoletoResponseDTO;
import com.example.efeito_mulher.dto.CartaoResponseDTO;
import com.example.efeito_mulher.dto.PagamentoRequestDTO;
import com.example.efeito_mulher.dto.PixResponseDTO;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@Service
public class PagamentoService {

    /* ---------- PIX ---------- */
    public PixResponseDTO gerarPix(BigDecimal total) {
        String valor = total.setScale(2).toPlainString();
        String qr = "00020126...5204000053039865405" + valor.replace(".", "")
                + "5802BR59" + UUID.randomUUID().toString().substring(0, 10)
                + "6304ABCD";
        return new PixResponseDTO(qr, "https://api.qrserver.com/v1/create-qr-code/?size=200x200&data=" + qr);
    }

    /* ---------- Boleto ---------- */
    public BoletoResponseDTO gerarBoleto(BigDecimal total) {
        String codBarras = "23791.11128 60001.234569 12345.678900 1 912300000" +
                total.setScale(2).toPlainString().replace(".", "");
        return new BoletoResponseDTO(
                "https://meusboletos.fake/boleto-" + UUID.randomUUID() + ".pdf",
                codBarras
        );
    }

    /* ---------- Cartão ---------- */
    public CartaoResponseDTO pagarCartao(PagamentoRequestDTO dto) {
        // Lógica fake de aprovação
        boolean aprovado = Math.random() > 0.05; // 95 % de aprovação
        return new CartaoResponseDTO(
                aprovado ? "APROVADO" : "RECUSADO",
                "AUTH" + (int)(Math.random()*900000+100000),
                String.valueOf((int)(Math.random()*90000000+10000000)),
                detectarBandeira(dto.getNumero()),
                aprovado ? "Pagamento autorizado" : "Pagamento recusado pelo emissor"
        );
    }

    private String detectarBandeira(String numero) {
        return numero != null && numero.startsWith("4") ? "VISA" :
                numero != null && numero.startsWith("5") ? "MASTERCARD" : "DESCONHECIDA";
    }
}