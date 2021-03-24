package br.com.alura.spring.data.service;

import br.com.alura.spring.data.orm.Cargo;
import br.com.alura.spring.data.orm.UnidadeTrabalho;
import br.com.alura.spring.data.repository.CargoRepository;
import br.com.alura.spring.data.repository.UnidadeTrabalhoRepository;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class CrudUnidadeTrabalhoService {

    private final UnidadeTrabalhoRepository repository;
    private Boolean system = true;

    public CrudUnidadeTrabalhoService(UnidadeTrabalhoRepository repository) {
        this.repository = repository;
    }

    public void inicial(Scanner scanner) {
        while (system) {
            System.out.println("Qual acao da unidade de trabalho deseja executar");
            System.out.println("0 - Sair");
            System.out.println("1 - Salvar");
            System.out.println("2 - Atualizar");
            System.out.println("3 - Visualizar");
            System.out.println("4 - Deletar");
            int acao = scanner.nextInt();

            switch (acao) {
                case 1:
                    salvar(scanner);
                    break;
                case 2:
                    atualizar(scanner);
                    break;
                case 3:
                    visualizar();
                    break;
                case 4:
                    deletar(scanner);
                    break;
                default:
                    system = false;
                    break;
            }
        }
    }

    private void salvar(Scanner scanner) {
        System.out.println("Digite o nome: ");
        String descricao = scanner.next();
        System.out.println("Digite a endereco: ");
        String endereco = scanner.next();

        UnidadeTrabalho unidadeTrabalho = new UnidadeTrabalho();
        unidadeTrabalho.setDescricao(descricao);
        unidadeTrabalho.setEndereco(endereco);

        repository.save(unidadeTrabalho);
        System.out.println("Salvo");
    }

    private void atualizar(Scanner scanner) {
        System.out.println("id");
        int id = scanner.nextInt();
        System.out.println("Digite o nome: ");
        String descricao = scanner.next();
        System.out.println("Digite a endereco: ");
        String endereco = scanner.next();

        UnidadeTrabalho unidadeTrabalho = new UnidadeTrabalho();
        unidadeTrabalho.setId(id);
        unidadeTrabalho.setDescricao(descricao);
        unidadeTrabalho.setEndereco(endereco);

        repository.save(unidadeTrabalho);
        System.out.println("Atualizado");
    }

    private void visualizar() {
        Iterable<UnidadeTrabalho> unidades = repository.findAll();
        unidades.forEach(unidade -> System.out.println(unidade.toString()));
    }

    private void deletar(Scanner scanner) {
        System.out.println("id");
        Integer id = scanner.nextInt();
        repository.deleteById(id);
        System.out.println("Deletado");
    }
}
