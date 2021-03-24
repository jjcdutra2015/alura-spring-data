package br.com.alura.spring.data;

import br.com.alura.spring.data.service.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class SpringDataApplication implements CommandLineRunner {

	private final CrudCargoService crudCargoService;
	private final CrudFuncionarioService crudFuncionarioService;
	private final CrudUnidadeTrabalhoService crudUnidadeTrabalhoService;
	private final RelatoriosServive relatoriosServive;
	private final RelatorioFuncionarioDinamico relatorioFuncionarioDinamico;

	private Boolean system = true;

	public SpringDataApplication(CrudCargoService crudCargoService, CrudFuncionarioService crudFuncionarioService,
								 CrudUnidadeTrabalhoService crudUnidadeTrabalhoService, RelatoriosServive relatoriosServive,
								 RelatorioFuncionarioDinamico relatorioFuncionarioDinamico) {
		this.crudCargoService = crudCargoService;
		this.crudFuncionarioService = crudFuncionarioService;
		this.crudUnidadeTrabalhoService = crudUnidadeTrabalhoService;
		this.relatoriosServive = relatoriosServive;
		this.relatorioFuncionarioDinamico = relatorioFuncionarioDinamico;
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringDataApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Scanner scanner = new Scanner(System.in);

		while (system) {
			System.out.println("Qual acao deseja executar");
			System.out.println("0 - Sair");
			System.out.println("1 - Cargo");
			System.out.println("2 - Funcionario");
			System.out.println("3 - Unidade de Trabalho");
			System.out.println("4 - Relatorios");
			System.out.println("5 - Relatorios dinamicos");

			int acao = scanner.nextInt();
			switch (acao) {
				case 1:
					crudCargoService.inicial(scanner);
					break;
				case 2:
					crudFuncionarioService.inicial(scanner);
					break;
				case 3:
					crudUnidadeTrabalhoService.inicial(scanner);
					break;
				case 4:
					relatoriosServive.inicial(scanner);
					break;
				case 5:
					relatorioFuncionarioDinamico.inicial(scanner);
					break;
				default:
					system = false;
					break;
			}
		}
	}
}
