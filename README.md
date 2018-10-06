# Compactador de arquivos

# Pipeline
	BWT -> RLE -> HUFFMAN

# Justificativa
	BWT junta caracteres similares em sequência para formar runs de bons comprimentos, e o RLE faz a compactação das runs. Após isso, o Huffman é usado para compactar e gerar o arquivo final, visto que ele dá um bom fator de compactação. 

# Fator de compactação
	Arquivo final fica com aprox. 50% do tamanho do arquivo original.

# Execução
	Desenvolvido no Eclipse, para rodar basta abrir o projeto na IDE e rodar, ou rodar por linha de comando
