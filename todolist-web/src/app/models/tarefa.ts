// src/app/models/tarefa.ts
export interface Tarefa {
  id?: number; // ID é opcional, pois o backend o gera na criação.
  descricao: string;
  concluida: boolean;
}
