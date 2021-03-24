package br.com.alura.spring.data.service;

import br.com.alura.spring.data.orm.Funcionario;
import br.com.alura.spring.data.orm.FuncionarioDto;
import br.com.alura.spring.data.orm.FuncionarioProjecao;
import br.com.alura.spring.data.repository.FuncionarioRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

@Service
public class RelatoriosServive {

    private Boolean system = true;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private final FuncionarioRepository funcionarioRepository;

    public RelatoriosServive(FuncionarioRepository funcionarioRepository) {
        this.funcionarioRepository = funcionarioRepository;
    }

    public void inicial(Scanner scanner) {
        while (system) {
            System.out.println("Qual acao do relatorio deseja executar");
            System.out.println("0 - Sair");
            System.out.println("1 - Buscar funcionarios pelo nome");
            System.out.println("2 - Buscar funcionarios pelo nome, data de contratacao e salario maior");
            System.out.println("3 - Buscar funcionarios pela data de contratacao maior");
            System.out.println("4 - Pesquisar funcionarios por salario");
            int acao = scanner.nextInt();

            switch (acao) {
                case 1:
                    buscarFuncionarioNome(scanner);
                    break;
                case 2:
                    buscarFuncionarioNomeSalarioMaiorDataContratacao(scanner);
                    break;
                case 3:
                    buscarFuncionarioDataContratacaoMaior(scanner);
                    break;
                case 4:
                    buscarFuncionarioSalario();
                    break;
                default:
                    system = false;
                    break;
            }
        }
    }

    private void buscarFuncionarioNome(Scanner scanner) {
        System.out.println("Digite o nome do funcionario: ");
        String nome = scanner.next();

        List<Funcionario> list = funcionarioRepository.findByNome(nome);
        list.forEach(System.out::println);
    }

    private void buscarFuncionarioNomeSalarioMaiorDataContratacao(Scanner scanner) {
        System.out.println("Digite o nome: ");
        String nome = scanner.next();
        System.out.println("Digite data contratacao: ");
        String data = scanner.next();
        LocalDate localDate = LocalDate.parse(data, formatter);
        System.out.println("Digite o salario: ");
        Double salario = scanner.nextDouble();

        List<Funcionario> list = funcionarioRepository.findNomeSalarioMaiorDataContratacao(nome, salario, localDate);
        list.forEach(System.out::println);
    }

    private void buscarFuncionarioDataContratacaoMaior(Scanner scanner) {
        System.out.println("Digite data contratacao: ");
        String data = scanner.next();
        LocalDate localDate = LocalDate.parse(data, formatter);

        List<Funcionario> list = funcionarioRepository.findDataContratacaoMaior(localDate);
        list.forEach(System.out::println);
    }

    private void buscarFuncionarioSalario() {
        List<FuncionarioDto> list = funcionarioRepository.findFuncionarioSalario();
        list.forEach(f -> System.out.println("Funcionario -> Id: " + f.getId() + " | Nome: " + f.getNome() + " | Salario: " + f.getSalario()));
    }
}
