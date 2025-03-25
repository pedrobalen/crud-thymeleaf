package com.example.demo.controller;

import com.example.demo.model.Produto;
import com.example.demo.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/produto")
public class ProdutoController {
    @Autowired
    private ProdutoRepository produtoRepository;

    @GetMapping("/novo")
    public String formNovo(Model model) {
        model.addAttribute("produto", new Produto());
        return "form";
    }

    @PostMapping
    public String salvar(@ModelAttribute Produto produto) {
        System.out.println(produto.toString());
        produtoRepository.save(produto);
        return "redirect:/produto/novo";
    }


    @GetMapping
    public String listar(Model model) {
        model.addAttribute("produtos", produtoRepository.findAll());
        return "lista";
    }

    @GetMapping("/deletar/{id}")
    public String deletar(@PathVariable Long id) {
        produtoRepository.deleteById(id);
        return "redirect:/produto";
    }

    @GetMapping("/editar/{id}")
    public String formEditar(@PathVariable Long id, Model model) {
        Produto produto = produtoRepository.findById(id).get();
        model.addAttribute("produto", produto);
        return "form";
    }
}
