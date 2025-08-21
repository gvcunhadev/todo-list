// src/app/components/task-list/task-list.component.ts
import { Component, OnInit } from '@angular/core';
import { TarefaService } from '../../services/tarefa.service';
import { Tarefa } from '../../models/tarefa';
import { CommonModule } from '@angular/common'; // Necessário para *ngFor, etc.
import { FormsModule } from '@angular/forms';   // Necessário para [(ngModel)]

@Component({
  selector: 'app-task-list',
  standalone: true,
  // Em componentes standalone, importamos os módulos que usamos diretamente aqui.
  imports: [
    CommonModule,
    FormsModule
  ],
  templateUrl: './task-list.component.html',
  styleUrl: './task-list.component.css'
})
export class TaskListComponent implements OnInit {

  tarefas: Tarefa[] = [];
  novaTarefa: Tarefa = { descricao: '', concluida: false };

  constructor(private tarefaService: TarefaService) { }

  // ngOnInit é executado quando o componente é inicializado.
  ngOnInit(): void {
    this.carregarTarefas();
  }

  carregarTarefas(): void {
    this.tarefaService.getTarefas().subscribe(tarefasRecebidas => {
      this.tarefas = tarefasRecebidas;
    });
  }

  adicionarTarefa(): void {
    if (this.novaTarefa.descricao.trim() === '') return;

    this.tarefaService.addTarefa(this.novaTarefa).subscribe(tarefaAdicionada => {
      this.tarefas.push(tarefaAdicionada);
      this.novaTarefa = { descricao: '', concluida: false }; // Limpa o formulário
    });
  }

  atualizarStatus(tarefa: Tarefa): void {
    this.tarefaService.updateTarefa(tarefa).subscribe();
  }

  deletarTarefa(id: number | undefined): void {
    if (id === undefined) return;

    this.tarefaService.deleteTarefa(id).subscribe(() => {
      // Remove a tarefa da lista local para atualizar a UI instantaneamente
      this.tarefas = this.tarefas.filter(t => t.id !== id);
    });
  }
}
