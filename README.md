# Compactador de arquivos

# Pipeline
	BWT -> RLE -> HUFFMAN (para arquivos TXT, para outros que conterem algum "@", apenas Huffman)

# Justificativa
	BWT junta caracteres similares em sequência para formar runs de bons comprimentos, e o RLE faz a compactação das runs. Após isso, o Huffman é usado para compactar e gerar o arquivo final, visto que ele dá um bom fator de compactação. 

# Fator de compactação
	Arquivo final fica com aprox. 50% do tamanho do arquivo original.

# Execução
	Desenvolvido no Eclipse, para rodar basta abrir o projeto na IDE e rodar, ou rodar por linha de comando. Se executar pelo JAR, passar o nome do arquivo como parametro. Quando o programa for executado ele vai compactar e descompactar o arquivo, ao fim do processo varios arquivos irão ser criados. O arquivo compactado será o "compactado.txt" e o descompactado "descompactado.txt".

# Observacão
	Foi notado que no arquivo alice29, ao fim do arquivo descompactado, não foi bem decodificado, pois não ficou exatamente como o arquivo original e ele aumentou um pouco de tamanho quando descompactado.
