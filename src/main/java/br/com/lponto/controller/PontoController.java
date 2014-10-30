package br.com.lponto.controller;

import java.awt.Dimension;
import java.util.Date;

import javax.inject.Inject;
import javax.transaction.Transactional;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.validator.Validator;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.util.ImageUtils;

import br.com.lponto.bean.Funcionario;
import br.com.lponto.bean.Ponto;
import br.com.lponto.enumeration.Register;
import br.com.lponto.repository.PontoRepository;
import br.com.lponto.session.EmployeeSession;
import br.com.lponto.session.PointSession;
import br.com.lponto.util.Utilities;

/**
 *
 * @author Phelipe Melanias
 */
@Controller
public class PontoController extends MainController {

    @Inject
    private PointSession pointSession;

    @Inject
    private PontoRepository pontoRepository;

    /**
     * @deprecated CDI eyes only
     */
    protected PontoController() {
        this(null, null, null);
    }

    @Inject
    public PontoController(Result result, Validator validator, EmployeeSession employeeSession) {
        super(result, validator, employeeSession);
    }

    @Get("/ponto/register")
    public void registerForm() {
        result.include("title", "Registrar ponto");
    }

    @Post("/ponto/register")
    public void addRegister() {
        Webcam webcam = Webcam.getDefault();

        //Capturar imagem com resolução 320x240 se possível
        if (isResolutionAvailable(320, 240, webcam.getViewSizes())) {
            webcam.setViewSize(new Dimension(320, 240));
        }

        webcam.open();
        byte[] picture = ImageUtils.toByteArray(webcam.getImage(), ImageUtils.FORMAT_PNG);
        webcam.close();

        //Funcionário
        Funcionario employee = new Funcionario();
        employee.setId(employeeSession.getId());

        //Ponto
        Ponto ponto = new Ponto();
        ponto.setHorario(new Date());
        ponto.setFuncionario(employee);

        //Definir tipo
        Ponto latest = pontoRepository.getLatestRecord(employee);

        if (latest == null) {
            ponto.setTipo(Register.ENTRADA);
        } else {
            if (latest.getTipo().equals(Register.ENTRADA)) {
                ponto.setTipo(Register.SAIDA);
            } else {
                ponto.setTipo(Register.ENTRADA);
            }
        }

        //Imagem do registro
        ponto.setFile(picture);
        ponto.setMimeType("image/png");
        ponto.setFileName(Utilities.md5(ponto.getHorario().toString()) +".png");

        pointSession.setPonto(ponto);
        result.redirectTo(this).registerForm();
    }

    @Get("/ponto/cancel")
    public void cancelRegister() {
        //Limpar sessão
        pointSession.setPonto(null);
        result.redirectTo(this).registerForm();
    }

    @Transactional
    @Post("/ponto/confirm")
    public void confirmRegister() {
        //Salvar dados do registro de ponto do respectivo funcionário
        pontoRepository.persist(pointSession.getPonto());
        result.include("successMessage", "Registro de ponto efetuado com sucesso.");

        //Limpar sessão
        pointSession.setPonto(null);
        result.redirectTo(this).registerForm();
    }

    private boolean isResolutionAvailable(int width, int height, Dimension[] viewSizes) {
        boolean w = false,
                h = false;

        for (Dimension dimension : viewSizes) {
            if (width == dimension.width) {
                w = true;
            }

            if (height == dimension.height) {
                h = true;
            }
        }

        return w && h;
    }
}