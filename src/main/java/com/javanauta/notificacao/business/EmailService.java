package com.javanauta.notificacao.business;

import com.javanauta.notificacao.business.dto.TarefasDTO;
import com.javanauta.notificacao.infrastructure.exceptions.EmailException;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

@Service
@RequiredArgsConstructor
public class EmailService {
    private final JavaMailSender javaMailSender;
    private final TemplateEngine templateEngine;

    @Value("${envio.email.remetente}")
    public String remetente;

    @Value("${envio.email.nomeRemetente}")
    public String nomeRemetente;

    public void enviaEmail(TarefasDTO  tarefaDTO) {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(message, true, StandardCharsets.UTF_8.name());
            mimeMessageHelper.setFrom(new InternetAddress(remetente, nomeRemetente));
            mimeMessageHelper.setTo(InternetAddress.parse(tarefaDTO.getEmailUsuario()));
            mimeMessageHelper.setSubject("Notificacao de Tarefa");

            Context context = new  Context();
            context.setVariable("nomeTarefa", tarefaDTO.getNome());
            context.setVariable( "dataEvento", tarefaDTO.getDataEvento());
            context.setVariable("descricao", tarefaDTO.getDescricao());
            String template = templateEngine.process("notificacao", context);
            mimeMessageHelper.setText(template, true);
            javaMailSender.send(message);

        } catch (MessagingException | UnsupportedEncodingException e) {
            throw new EmailException("Erro ao enviar email", e.getCause());
        }

    }

}
