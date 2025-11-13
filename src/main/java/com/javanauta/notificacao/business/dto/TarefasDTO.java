package com.javanauta.notificacao.business.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.javanauta.notificacao.business.enums.StatusNotificaoEnum;
import lombok.*;

import java.time.LocalDateTime;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TarefasDTO {
        private String id;
        private String nome;
        private String descricao;
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
        private LocalDateTime dataCriacao;
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
        private LocalDateTime dataEvento;
        private String emailUsuario;
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
        private LocalDateTime dataAlteracao;
        private StatusNotificaoEnum statusNotificaoEnum;

}
