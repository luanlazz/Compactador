# Compactador e descompactador de arquivos

# Pipeline
BWT -> RLE -> HUFFMAN

# Justificativa
BWT: Busca sequências de mesmos caracteres (runs) e as reorganiza afim de deixá-las juntas
RLE: Compacta as runs no formado "ax", onde a = caracter da run e x = qtd de ocorrências do caracter
HUFFMAN: Algoritmo base para compactação utilizado por vários outros algoritmos. Dá um bom fator de compactação no resultado final

# Executando
Projeto desenvolvido em Java, para rodar execute por linha de comando ou utilizando uma IDE.
